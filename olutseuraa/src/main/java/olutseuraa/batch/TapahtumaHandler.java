package olutseuraa.batch;

import java.util.List;

import olutseuraa.bean.Tapahtuma;
import olutseuraa.dao.TapahtumaDAO;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
			System.out.println("is‰nt‰: " + t.getIsanta());
			System.out.println("kuvaus: " + t.getKuvaus());
		}
		System.out.println("-------------------");
		
		System.out.println("P‰ivitet‰‰n tapahtuma, jonka id on 1.");
		//haetaan ensin tapahtuma, id:ll‰
		Tapahtuma t1 = dao.haeTapahtuma(1);
		//haetaan alkuper‰inen osallistujam‰‰r‰ ja lis‰t‰‰n siihen yksi.
		int osallistujamaara = t1.getOsallistujat() + 1;
		//p‰ivitet‰‰n osallistujam‰‰r‰ tietokantaan
		dao.paivitaOsallistujat(t1.getId(), osallistujamaara);
		//p‰ivitet‰‰n tapahtuma serverill‰, eli haetaan p‰ivitetty tieto tietokannasta
		t1 = dao.haeTapahtuma(1);
		System.out.println("Tapahtuman " + t1.getNimi() + " osallistujam‰‰r‰ on nyt: " + t1.getOsallistujat());
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
			System.out.println("is‰nt‰: " + t.getIsanta());
			System.out.println("kuvaus: " + t.getKuvaus());
		}
		System.out.println("-------------------");
		
		
		context.close();
	}

}
