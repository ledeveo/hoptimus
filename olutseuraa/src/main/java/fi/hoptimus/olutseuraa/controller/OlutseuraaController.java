package fi.hoptimus.olutseuraa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		System.out.println();

		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		Henkilo tyhjaHenkilo = new HenkiloImpl();
		tuoKuukaudet(model);

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
			@ModelAttribute(value = "henkilo") HenkiloImpl henkilo,
			@RequestParam Map<String, String> requestParams) {

		String eId = requestParams.get("eventid");

		if(henkilo.getEtunimi().isEmpty() || henkilo.getSukunimi().isEmpty() || henkilo.getSahkoposti().isEmpty()){
			return "redirect:kaikki";
		}
		
			dao.talleta(henkilo); // tallettaa henkilon tietokantaan ja
									// palauttaa sen
			// id:llä

			dao.liityTapahtumaan(henkilo, eId);

			return "redirect:kaikki";
		}
	
	private void tuoKuukaudet(Model model){
		
		List<String> kuukaudet = new ArrayList<String>();
		kuukaudet.add("Tammikuu");
		kuukaudet.add("Helmikuu");
		kuukaudet.add("Maaliskuu");
		kuukaudet.add("Huhtikuu");
		kuukaudet.add("Toukokuu");
		kuukaudet.add("Kesäkuu");
		kuukaudet.add("Heinäkuu");
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
