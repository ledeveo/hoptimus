package fi.hoptimus.olutseuraa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
		//hae henkilo TODO: id?
		int id = 0;
		Henkilo h = dao.haeHenkilo(id);
		model.addAttribute("henkilo", h);
		
		//hae henkilön tapahtumat
		List<Tapahtuma> t = dao.haeHenkilonTapahtumat(h);
		model.addAttribute("tapahtumat", t);
		
		return "tapah/userpage";
	}

	// Aktivointilomakkeen näyttäminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.GET)
	public String naytaAktivointiSivu(@PathVariable Integer id, Model model) {
		
		model.addAttribute("id", id);
		
		return "tapah/aktivointi";
	}
		
	// Aktivointilomakkeen tallentaminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.POST)
	public String AktivoiTunnus(@PathVariable Integer id, Model model,
			@RequestParam Map<String, String> requestParams) {
		
		Henkilo oikeahenkilo = dao.haeHenkilo(id);
		String sahkoposti = requestParams.get("sahkoposti");
		String salasana1 = requestParams.get("salasana");
		String salasana2 = requestParams.get("salasana2");
		
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
				return "redirect:aktivoi?id=" + oikeahenkilo.getId();
			}
		} else {
			//salasanat ei täsmää
			model.addAttribute("submitError", true);
			return "redirect:aktivoi?id=" + oikeahenkilo.getId();
		}
		
	}
	
	// TAPAHTUMAN TIETOJEN N�YTT�MINEN
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Tapahtuma tapahtuma = dao.haeTapahtuma(id);
		model.addAttribute("tapahtuma", tapahtuma);
		return "tapah/view";
	}
	
	@PostMapping("/liity")
	public String liita(
			@ModelAttribute(value = "henkilo") @Valid HenkiloImpl henkilo,
			@RequestParam Map<String, String> requestParams) {

		String eId = requestParams.get("eventid");

		if(henkilo.getEtunimi().isEmpty() || henkilo.getSukunimi().isEmpty() || henkilo.getSahkoposti().isEmpty()){
			return "redirect:tapahtumat";
		}
		
			dao.talleta(henkilo); // tallettaa henkilon tietokantaan ja
									// palauttaa sen
			// id:ll�

			dao.liityTapahtumaan(henkilo, eId);

			//luodaan simple message
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("testimeilihoptimus@gmail.com");
			mail.setTo(henkilo.getSahkoposti());
			mail.setSubject("Hei " + henkilo.getEtunimi() +"! Aktivoi tunnuksesi olutseuran sivuille!");
			
			//linkki
			//String linkki = "http://proto285:8080/olutseuraa/aktivoi?id=" + henkilo.getId(); //protolle ohjaus
			String linkki  ="http://localhost:8080/olutseuraa/aktivoi?id=" + henkilo.getId(); //localhostilla kikkailua varten
			
			mail.setText("Hei " + henkilo.getEtunimi() + "! Olet osallistunut tapahtumaan Olutseuraa-sivuilla. Mene tähän linkkiin aktivoidaksesi tunnuksesi: " + linkki + " - Hoptimus Team.");
			
			//lähetetään se käyttäjän sähköpostiin
			mailer.send(mail);
			
			return "redirect:tapahtumat";
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
