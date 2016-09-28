package fi.hoptimus.olutseuraa.batch;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.dao.TapahtumaDAO;

public class TapahtumaHandler {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		TapahtumaDAO dao = (TapahtumaDAO)context.getBean("daoLuokka");
		
		System.out.println("Haetaan tapahtumat");
		System.out.println("-------------------");
		List<Tapahtuma> tapahtumat = dao.haeKaikki();
		for(Tapahtuma t : tapahtumat) {
			System.out.println("id: " + t.getId());
			System.out.println("nimi: " + t.getNimi());
			System.out.println("pvm: " + t.getPvm());
			System.out.println("aika: " + t.getAika());
			System.out.println("paikka: " + t.getPaikka());
			System.out.println("teema: " + t.getTeema());
			System.out.println("osallistujat: " + t.getOsallistujat());
			System.out.println("isäntä: " + t.getIsanta());
			System.out.println("kuvaus: " + t.getKuvaus());
		}
		System.out.println("-------------------");
		
		System.out.println("Päivitetään tapahtuma, jonka id on 1.");
		//haetaan ensin tapahtuma, id:llä
		Tapahtuma t1 = dao.haeTapahtuma(1);
		//haetaan alkuperäinen osallistujamäärä ja lisätään siihen yksi.
		//int osallistujamaara = t1.getOsallistujat();
		//asetetaan osallistujamäärä oliolle
		//t1.setOsallistujat(osallistujamaara);
		//päivitetään tapahtuma tietokantaan
		dao.paivitaTapahtuma(t1);
		System.out.println("Tapahtuman " + t1.getNimi() + " osallistujamäärä on nyt: " + t1.getOsallistujat());
		System.out.println("-------------------");
		
		System.out.println("Haetaan tapahtumat");
		System.out.println("-------------------");
		tapahtumat = dao.haeKaikki();
		for(Tapahtuma t : tapahtumat) {
			System.out.println("id: " + t.getId());
			System.out.println("nimi: " + t.getNimi());
			System.out.println("pvm: " + t.getPvm());
			System.out.println("aika: " + t.getAika());
			System.out.println("paikka: " + t.getPaikka());
			System.out.println("teema: " + t.getTeema());
			System.out.println("osallistujat: " + t.getOsallistujat());
			System.out.println("isäntä: " + t.getIsanta());
			System.out.println("kuvaus: " + t.getKuvaus());
		}
		System.out.println("-------------------");
		
		
		context.close();
	}

}
