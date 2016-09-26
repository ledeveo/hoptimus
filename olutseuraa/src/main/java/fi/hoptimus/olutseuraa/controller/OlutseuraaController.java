package fi.hoptimus.olutseuraa.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.dao.TapahtumaDAO;

import java.util.List;

@Controller
@RequestMapping (value="/tapahtumat")
public class OlutseuraaController {
	
	@Inject
	private TapahtumaDAO dao;
	
	public TapahtumaDAO getDao() {
		return dao;
	}
	
	public void setDao(TapahtumaDAO dao) {
		this.dao = dao;
	}
	
	//TODO:FORMIN TEKEMINEN | Tapahtuman luonti formi
	
	//TODO:FORMIN TIETOJEN VASTAANOTTO & TALLETUS
	
	//TODO:TAPAHTUMIEN TIETOJEN N�YTT�MINEN | n�yt� kaikki tapahtumat
	@RequestMapping(value="kaikki", method=RequestMethod.GET)
	public String getView(Model model) {
		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		model.addAttribute("tapahtumat", tapahtumat);
		return "tapah/all";
	}
	
	//TAPAHTUMAN TIETOJEN N�YTT�MINEN
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Tapahtuma tapahtuma = dao.haeTapahtuma(id);
		model.addAttribute("tapahtuma", tapahtuma);
		return "tapah/view";
	}
	
}