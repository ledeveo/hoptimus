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
			System.out.println("isäntä: " + t.getIsanta());
			System.out.println("kuvaus: " + t.getKuvaus());
		}
		System.out.println("-------------------");
		
		context.close();
	}

}
