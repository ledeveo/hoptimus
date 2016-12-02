package fi.hoptimus.olutseuraa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.HenkiloImpl;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.bean.TapahtumaImpl;
import fi.hoptimus.olutseuraa.dao.TapahtumaDAO;
import fi.hoptimus.olutseuraa.helper.Helpperi;

@Controller
@RequestMapping(value = "/")
public class OlutseuraaController {

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	
	@Inject
	private TapahtumaDAO dao;

	public TapahtumaDAO getDao() {
		return dao;
	}

	public void setDao(TapahtumaDAO dao) {
		this.dao = dao;
	}
	
	@Inject
	private MailSender mailer;
	
	public MailSender getMailer() {
		return mailer;
	}
	
	public void setMailer(MailSender mailer) {
		this.mailer = mailer;
	}
	
	// FORMIN TEKEMINEN | Tapahtuman luonti formi
	@RequestMapping(value = "uusi", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		Tapahtuma tyhjaTapahtuma = new TapahtumaImpl();
		// tyhjaTapahtuma.setNimi("halleluja");
		initModelList(model);
		model.addAttribute("tapahtuma", tyhjaTapahtuma);
		return "tapah/luoTapahtuma";
	}

	// FORMIN TIETOJEN VASTAANOTTO & TALLETUS
	@RequestMapping(value = "uusi", method = RequestMethod.POST)
	public String create(
			Model model, @ModelAttribute(value = "tapahtuma") @Valid TapahtumaImpl tapahtuma, BindingResult result) {
		
		//TODO: tähän tarvitaa testi onko käyttäjä admin!
		
		System.out.println(result);
		
		if (result.hasErrors()) {
			System.out.println(result);
			model.addAttribute("submitError", "true"); //vie tieto jsp:hen että virhe lisättäessä tapahtumaa.
			return "redirect:uusi";
		} else {
			dao.talleta(tapahtuma);
			return "redirect:tapahtumat";
		}
	}

	//muokkaaTapahtuma
	@RequestMapping(value = "muokkaaTapahtuma", method = RequestMethod.POST)
	public String muokkaaTapahtumaa(Model model,
			@RequestParam Map<String, String> requestParams) {
		
		String toiminto = requestParams.get("toiminto");
		int tapId = Integer.parseInt(requestParams.get("tapahId"));
		
		
		if(toiminto.equals("tallenna")) {
			
			//tän vois tehrä springin modellilla mut meh.
			String aika = requestParams.get("aika");
			String isanta = requestParams.get("isanta");
			String kuvaus = requestParams.get("kuvaus");
			int maxOsallistujamaara = Integer.parseInt(requestParams.get("maxOsallistujamaara2"));
			String nimi = requestParams.get("nimi");
			String paikka = requestParams.get("paikka");
			java.util.Date pvm2 = null;
			try {
				pvm2 = f.parse(requestParams.get("pvm"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date pvm = new Date(pvm2.getTime());
			String teema = requestParams.get("teema");
			
			//DEBUG
			System.out.println("maxOsallistujia: " + maxOsallistujamaara);
			
			//hae formin tiedot
			Tapahtuma t = new TapahtumaImpl();
			t.setId(tapId);
			t.setAika(aika);
			t.setIsanta(isanta);
			t.setKuvaus(kuvaus);
			t.setmaxOsallistujamaara(maxOsallistujamaara);
			t.setNimi(nimi);
			t.setPaikka(paikka);
			t.setPvm(pvm);
			t.setTeema(teema);
			
			System.out.println("tapahtuman max osallistujat: " + t.getmaxOsallistujamaara());
			//päivitä tapahtuman tiedot
			dao.paivitaTapahtuma(t);
			
		} else if(toiminto.equals("poista")) {
			
			//poista tapahtuma
			dao.poistaTapahtuma(tapId);
		}
		
		return "redirect:tapahtumat";
	}
	
	// n�yt� kaikki tapahtumat
	@RequestMapping(value = "tapahtumat", method = RequestMethod.GET)
	public String getView(Model model) {
		
		//System.out.println("Kaikki tapahtumat!!!");

		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		
		Henkilo tyhjaHenkilo = new HenkiloImpl();
		tuoKuukaudet(model);
		
		/*
		 * DEBUG
		for (int i = 0; i < tapahtumat.size(); i++) {
			System.out.println(tapahtumat.get(i));
		}
		*/

		model.addAttribute("henkilo", tyhjaHenkilo);
		model.addAttribute("tapahtumat", tapahtumat);

		return "tapah/all";
	}

	// TAPAHTUMAN TIETOJEN N�YTT�MINEN
	@RequestMapping(value = "userpage", method = RequestMethod.GET)
	public String getUserView(Model model) {
		//hae henkilö joka kirjautui
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String sahkoposti = auth.getName(); //get logged in username = sahkoposti
	    Henkilo h = dao.haeHenkilo(sahkoposti);
	    if(h != null) {
	    	model.addAttribute("henkilo", h);
			
			//hae henkilön tapahtumat
			List<Tapahtuma> t = dao.haeHenkilonTapahtumat(h);
			model.addAttribute("tapahtumat", t);
			
			return "tapah/userpage";
		} else {
			//jos ei olla kirjautuneena, ohjataan kirjautumaan
			return "redirect:login";
		}
	}

	//PoistaLiittyminen
	@RequestMapping(value = "/PoistaLiittyminen", method = RequestMethod.POST)
	public String PoistaLiittyminen(Model model,
			@RequestParam Map<String, String> requestParams) {
		
		//hae henkilö joka on kirjautunut
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String sahkoposti = auth.getName(); //get logged in username = sahkoposti
	    Henkilo h = dao.haeHenkilo(sahkoposti);
	    
	    int tapahtumaId = Integer.parseInt(requestParams.get("tapahtumaId"));
		
		//poista käyttäjältä tapahtumaan liittyminen
		dao.poistaLiittyminen(h, tapahtumaId);
		model.addAttribute("tapahtumaRemoved", true);
	    
		return "redirect:userpage";
	}
	
	//LisaaLiittyminen, plus napista käyttäjäsivulla, lisää yhden liittymisen
	@RequestMapping(value = "/LisaaLiittyminen", method = RequestMethod.POST)
	public String LisaaLiittyminen(Model model,
			@RequestParam Map<String, String> requestParams) {

		//hae henkilö joka on kirjautunut
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String sahkoposti = auth.getName(); //get logged in username = sahkoposti
	    Henkilo h = dao.haeHenkilo(sahkoposti);
	    
		int tapahtumaId = Integer.parseInt(requestParams.get("tapahtumaId"));
		
		Tapahtuma t = dao.haeTapahtuma(tapahtumaId);
		
		//tarkista että max osallistujamäärä ei ole täynnä
		if(t.getOsallistujat().size() <= t.getmaxOsallistujamaara()) {
			//lisää käyttäjälle yhden tapahtumaan liittyminen
			dao.liityTapahtumaan(h, tapahtumaId);
			model.addAttribute("tapahtumaAdded", true);
		} else {
			//muulloin ei pysty liittymään
			model.addAttribute("cantJoin", true);
		}
		
		return "redirect:userpage";
	}
	
	// Aktivointilomakkeen näyttäminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.GET)
	public String naytaAktivointiSivu(@PathVariable Integer id, Model model) {
		
		Henkilo h = dao.haeHenkilo(id);
		
		if(h != null) {
			if(h.isAktivoitu() == false) {
				//tili ei vielä aktivoitu
				model.addAttribute("userNotActivated", true);
				model.addAttribute("id", id);
				return "tapah/aktivointi";
			} else {
				model.addAttribute("user", true);
				//tili jo aktivoitu
				return "redirect:login"; //ohjaa loginsivulle
			}
		} else {
			//henkilöä id:llä ei löytynyt
			model.addAttribute("userNotExists", true);
			return "redirect:tapahtumat"; //ohjaa etusivulle jos id:llä ei löydy käyttäjää.
		}
		
	}
		
	// Aktivointilomakkeen tallentaminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.POST)
	public String AktivoiTunnus(@PathVariable Integer id, Model model,
			@RequestParam Map<String, String> requestParams) {
		System.out.println("id: " + id);
		Henkilo oikeahenkilo = dao.haeHenkilo(id);
		String sahkoposti = requestParams.get("sahkoposti");
		String salasana1 = requestParams.get("salasana");
		String salasana2 = requestParams.get("salasana2");
		
		System.out.println(oikeahenkilo.isAktivoitu());
		
		if(oikeahenkilo.isAktivoitu() == false) {
			//vertaa että salasanat täsmää
			if(salasana1.equals(salasana2)) {
				//vertaa että onko annettu sähköposti oikea
				if(sahkoposti.equals(oikeahenkilo.getSahkoposti())) {
					model.addAttribute("submitSuccess", true);
					
					//suolaa salasana ja aseta aktivoitu=true.
					StandardPasswordEncoder spe = new StandardPasswordEncoder();
					String suolattuSalasana = spe.encode(salasana1);
					oikeahenkilo.setSalasana(suolattuSalasana); 
					oikeahenkilo.setAktivoitu(true);
					
					//talleta tietokantaan salasana ja aktivointi
					dao.paivitaHenkilo(oikeahenkilo);
					
					dao.luoWebUserTili(oikeahenkilo);
					
					Helpperi.lahetaAktivointiOnnistunutlinkki(oikeahenkilo, mailer);
					
					return "login"; //ohjaa loginsivulle
				} else {
					//sähköposti ei täsmää
					model.addAttribute("submitError", true);
					return "redirect:aktivoi" + id;
				}
			} else {
				//salasanat ei täsmää
				model.addAttribute("submitError", true);
				return "redirect:aktivoi" + id;
			}
		} else {
			return "login"; //ohjaa loginsivulle
		}
		
		
	}
	
	// TAPAHTUMAN TIETOJEN N�YTT�MINEN
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Tapahtuma tapahtuma = dao.haeTapahtuma(id);
		model.addAttribute("tapahtuma", tapahtuma);
		return "tapah/view";
	}
	
	//Liity tapahtumaan kirjautuneella käyttäjällä
	@RequestMapping(value = "/kirjautunutLiity", method = RequestMethod.POST)
	public String kirjautunutliita(@RequestParam Map<String, String> requestParams, Model model) {

		//hae henkilö joka kirjautui
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String sahkoposti = auth.getName(); //get logged in username = sahkoposti
	    Henkilo henkilo = dao.haeHenkilo(sahkoposti);
	    int eId = Integer.parseInt(requestParams.get("eventid2"));
	    int osallistujamaara = Integer.parseInt(requestParams.get("osallistujamaara"));
	    
	    if(henkilo != null) {
	    	
	    	Tapahtuma t = dao.haeTapahtuma(eId);
			
			//tarkista että max osallistujamäärä ei ole täynnä
			if((t.getOsallistujat().size() + osallistujamaara) <= t.getmaxOsallistujamaara()) {
				
				//lisää käyttäjän liittymiset
				for(int i = 0; i < osallistujamaara; i++) {
					dao.liityTapahtumaan(henkilo, eId);
				}
				
				Helpperi.lahetaTapahtumaanLiittyminenOnnistunut(henkilo, t, mailer);
				
				model.addAttribute("tapahtumaAdded", true);
				return "redirect:userpage";
				
			} else {
				//muulloin ei pysty liittymään
				model.addAttribute("cantJoin", true);
				return "redirect:tapahtumat";
			}
			
	    	//lisää osallistujamäärän verran henkilöitä tapahtumaan
			
	    } else {
	    	model.addAttribute("submitError", true);
	    	return "redirect:tapahtumat";
	    }
		
	}
	
	@PostMapping("/liity")
	public String liita(
			@ModelAttribute(value = "henkilo") @Valid HenkiloImpl henkilo,
			@RequestParam Map<String, String> requestParams, Model model) {

		int eId = Integer.parseInt(requestParams.get("eventid"));
		int osallistujamaara = Integer.parseInt(requestParams.get("osallistujamaara"));
	    
		Henkilo h1 = dao.haeHenkilo(henkilo.getSahkoposti());
		
		
		//tarkista onko käyttäjä jo olemassa, anna virheilmoitus jos on.
		if(h1 != null){
			//käyttäjä on jo olemassa
			model.addAttribute("UserExists", true);
			return "redirect:tapahtumat";
		} else {
			henkilo = (HenkiloImpl) dao.talleta(henkilo); // tallettaa henkilon tietokantaan ja
			// palauttaa sen
			// id:ll�
			Tapahtuma t = dao.haeTapahtuma(eId);
			
			if((t.getOsallistujat().size() + osallistujamaara) <= t.getmaxOsallistujamaara()) {
					
				//lisää osallistujamäärän verran henkilöitä tapahtumaan
				for(int i = 0; i < osallistujamaara; i++) {
					dao.liityTapahtumaan(henkilo, eId);
				}
				
				Helpperi.lahetaAktivointilinkki(henkilo, t, mailer);
				model.addAttribute("submitSuccessActivate", true);
				return "redirect:tapahtumat";
				
			} else {
				//tapahtumaan ei voi liittyä näin monta kertaa
				model.addAttribute("submitError", true);
				return "redirect:tapahtumat";
			}
			
			
		}
	}
	
	
	@PostMapping("/palautetta")
	@ResponseBody
		public String tallennaPalaute(@RequestParam Map<String, String> requestParams){
		
		String sposti = requestParams.get("sposti");
		String nimi = requestParams.get("nimi");
		String palaute = requestParams.get("palaute");
		
		System.out.println("Sähköposti: " + sposti + ", nimi: " + nimi + ", palaute: " + palaute);
		String success = "A succesful ajax-request!";
		
		return success;                                
	}
	private void tuoKuukaudet(Model model){
		
		List<String> kuukaudet = new ArrayList<String>();
		kuukaudet.add("Tammikuu");
		kuukaudet.add("Helmikuu");
		kuukaudet.add("Maaliskuu");
		kuukaudet.add("Huhtikuu");
		kuukaudet.add("Toukokuu");
		kuukaudet.add("Kes�kuu");
		kuukaudet.add("Hein�kuu");
		kuukaudet.add("Elokuu");
		kuukaudet.add("Syyskuu");
		kuukaudet.add("Lokakuu");
		kuukaudet.add("Marraskuu");
		kuukaudet.add("Joulukuu");
		
		model.addAttribute("kuukaudet", kuukaudet);
		
	}
	

	private void initModelList(Model model) {

		List<Integer> osallistujat = new ArrayList<Integer>();
		osallistujat.add(5);
		osallistujat.add(10);
		osallistujat.add(20);
		osallistujat.add(50);
		osallistujat.add(100);
		
		model.addAttribute("osallistujat", osallistujat);		

	}

}
