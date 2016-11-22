package fi.hoptimus.olutseuraa.controller;

import java.util.ArrayList;
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

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.HenkiloImpl;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.bean.TapahtumaImpl;
import fi.hoptimus.olutseuraa.dao.TapahtumaDAO;
import fi.hoptimus.olutseuraa.helper.Helpperi;

@Controller
@RequestMapping(value = "/")
public class OlutseuraaController {

	@Inject
	private TapahtumaDAO dao;

	public TapahtumaDAO getDao() {
		return dao;
	}

	public void setDao(TapahtumaDAO dao) {
		this.dao = dao;
	}
	
	@Inject MailSender mailer;
	
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

	// n�yt� kaikki tapahtumat
	@RequestMapping(value = "tapahtumat", method = RequestMethod.GET)
	public String getView(Model model) {
		
		System.out.println("Kaikki tapahtumat!!!");

		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		
		Henkilo tyhjaHenkilo = new HenkiloImpl();
		tuoKuukaudet(model);
		
		
		for (int i = 0; i < tapahtumat.size(); i++) {
			System.out.println(tapahtumat.get(i));
		}
		

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
			//jos ei olla kirjautuneena, ohjataan etusivulle
			return "redirect:tapahtumat";
		}
		
		
	}

	// Aktivointilomakkeen näyttäminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.GET)
	public String naytaAktivointiSivu(@PathVariable Integer id, Model model) {
		
		Henkilo h = dao.haeHenkilo(id);
		if(h != null) {
			if(h.isAktivoitu() == false) {
				//tili ei vielä aktivoitu
				model.addAttribute("id", id);
				return "tapah/aktivointi";
			} else {
				//tili jo aktivoitu
				return "login"; //ohjaa loginsivulle
			}
		} else {
			//henkilöä id:llä ei löytynyt
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
					
					//lähetetään aktivoinnista ilmoitus käyttäjälle sähköpostiin
					SimpleMailMessage mail = new SimpleMailMessage();
					mail.setFrom("testimeilihoptimus@gmail.com");
					mail.setTo(oikeahenkilo.getSahkoposti());
					mail.setSubject("Hei " + oikeahenkilo.getEtunimi() + "! Tilisi on aktivoitu!");
					String linkki = "http://localhost:8080/olutseuraa/login";
					//String linkki = "http//proto285:8080/olutseuraa/login";
					mail.setText("Hei " + oikeahenkilo.getEtunimi() + "! Olet aktivoinut onnistuneesti tilisi. Pääset katsomaan profiiliasi kirjautumalla Olutseuraa-sivuilla: " + linkki + " - Hoptimus Team.");
					//lähetetään se käyttäjän sähköpostiin
					mailer.send(mail);
					
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
	    	
	    	//lisää osallistujamäärän verran henkilöitä tapahtumaan
			for(int i = 0; i < osallistujamaara; i++) {
				dao.liityTapahtumaan(henkilo, eId);
			}
			

			Tapahtuma t = dao.haeTapahtuma(eId);
			
			//luodaan simple message
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("testimeilihoptimus@gmail.com");
			mail.setTo(henkilo.getSahkoposti());
			mail.setSubject("Hei " + henkilo.getEtunimi() +"! Olet liittynyt tapahtumaan " + t.getNimi() + "!");
			
			//linkki
			String linkki = "http://proto285:8080/olutseuraa/login"; //protolle ohjaus
			//String linkki  ="http://localhost:8080/olutseuraa/login"; //localhostilla kikkailua varten
			
			mail.setText("Hei " + henkilo.getEtunimi() + "! Olet osallistunut tapahtumaan " + t.getNimi() + " Olutseuraa-sivuilla." +
			" Tapahtuma alkaa " + t.getPvm() + " klo " + t.getAika() + " paikassa: " + t.getPaikka() +
			" Voit tarkistella tapahtumiasi käyttäjäsivulla kirjautumalla sisään: " + linkki + " - Hoptimus Team.");
			
			//lähetetään se käyttäjän sähköpostiin
			mailer.send(mail);
			
			return "redirect:userpage";
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
			
			//lisää osallistujamäärän verran henkilöitä tapahtumaan
			for(int i = 0; i < osallistujamaara; i++) {
				dao.liityTapahtumaan(henkilo, eId);
			}
			
			Tapahtuma t = dao.haeTapahtuma(eId);
			
			//luodaan simple message
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("testimeilihoptimus@gmail.com");
			mail.setTo(henkilo.getSahkoposti());
			mail.setSubject("Hei " + henkilo.getEtunimi() +"! Aktivoi tunnuksesi olutseuran sivuille!");
			
			//linkki
			String linkki = "http://proto285:8080/olutseuraa/aktivoi" + henkilo.getId(); //protolle ohjaus
			//String linkki  ="http://localhost:8080/olutseuraa/aktivoi" + henkilo.getId(); //localhostilla kikkailua varten
			
			mail.setText("Hei " + henkilo.getEtunimi() + "! Olet osallistunut tapahtumaan " + t.getNimi() + " Olutseuraa-sivuilla." + 
					" Tapahtuma alkaa " + t.getPvm() + " klo " + t.getAika() + " paikassa: " + t.getPaikka() +
					" Mene tähän linkkiin aktivoidaksesi tunnuksesi: " + linkki + " Samalla vahvistat osallistumisen tapahtumaan. - Hoptimus Team.");
			
			//lähetetään se käyttäjän sähköpostiin
			mailer.send(mail);
			
			return "redirect:tapahtumat";
		}
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
