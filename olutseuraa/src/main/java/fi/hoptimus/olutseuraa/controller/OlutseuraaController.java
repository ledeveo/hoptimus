package fi.hoptimus.olutseuraa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

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
@RequestMapping(value = "/tapahtumat")
public class OlutseuraaController {

	@Inject
	private TapahtumaDAO dao;

	public TapahtumaDAO getDao() {
		return dao;
	}

	public void setDao(TapahtumaDAO dao) {
		this.dao = dao;
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
			@ModelAttribute(value = "tapahtuma") TapahtumaImpl tapahtuma) {
		dao.talleta(tapahtuma);
		return "redirect:/tapahtumat/" + tapahtuma.getId();
	}

	// näytä kaikki tapahtumat
	@RequestMapping(value = "kaikki", method = RequestMethod.GET)
	public String getView(Model model) {

		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		Henkilo tyhjaHenkilo = new HenkiloImpl();

		model.addAttribute("henkilo", tyhjaHenkilo);
		model.addAttribute("tapahtumat", tapahtumat);

		return "tapah/all";
	}

	// TAPAHTUMAN TIETOJEN NÄYTTÄMINEN
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Tapahtuma tapahtuma = dao.haeTapahtuma(id);
		model.addAttribute("tapahtuma", tapahtuma);
		return "tapah/view";
	}

	@PostMapping("/liity")
	public String liita(
			@ModelAttribute(value = "henkilo") @Valid HenkiloImpl henkilo,
			BindingResult result,
			@RequestParam Map<String, String> requestParams) {

		String eId = requestParams.get("eventid");

		/*
		 * String enimi = requestParams.get("etunimi"); String snimi =
		 * requestParams.get("sukunimi"); String sposti =
		 * requestParams.get("sposti");
		 * 
		 * System.out.println("Liity -servicessä hlo: " + enimi + ", " + snimi +
		 * ", sähköposti: " + sposti);
		 * System.out.println("Haluaa liittyä tapahtumaan nro: " + eId);
		 * 
		 * Henkilo h = new HenkiloImpl(); h.setEtunimi(enimi);
		 * h.setSukunimi(snimi); h.setSahkoposti(sposti);
		 */
		if (result.hasErrors()) {
			return "tapah/all";
		} else {
			dao.talleta(henkilo); // tallettaa henkilon tietokantaan ja
									// palauttaa sen
			// id:llä

			dao.liityTapahtumaan(henkilo, eId);

			return "redirect:kaikki";
		}

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
