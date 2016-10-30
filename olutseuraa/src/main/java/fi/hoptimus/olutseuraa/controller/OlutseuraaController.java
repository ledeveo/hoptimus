package fi.hoptimus.olutseuraa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Tapahtuma tapahtuma = dao.haeTapahtuma(id);
		model.addAttribute("tapahtuma", tapahtuma);
		return "tapah/view";
	}

	// Aktivointilomakkeen näyttäminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.GET)
	public String naytaAktivointiSivu(@PathVariable Integer id, Model model) {
		
		return "tapah/aktivointi";
	}
	
	// TODO: tallenna salasanat käyttäjille, luo daohon dao.haeHenkilo(id:llä) ja dao.aktivoiHenkilo(henkilo).
	/*
	// Aktivointilomakkeen näyttäminen
	@RequestMapping(value = "/aktivoi{id}", method = RequestMethod.POST)
	public String AktivoiTunnus(@PathVariable Integer id, Model model, @ModelAttribute(value = "henkilo") HenkiloImpl henkilo) {
		
		Henkilo oikeahenkilo = dao.haeHenkilo(id);
		//vertaa että onko annettu sähköposti oikea
		if(henkilo.getSahkoposti().equals(oikeahenkilo.getSahkoposti())) {
			model.addAttribute("SubmitSuccess", true);
			oikeahenkilo.setSalasana(henkilo.getSalasana());
			dao.aktivoiHenkilo(oikeahenkilo);
			
		} else {
			model.addAttribute("SubmitError", true);
		}
		
		return "tapah/aktivointi";
		
	}
	*/
	
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
