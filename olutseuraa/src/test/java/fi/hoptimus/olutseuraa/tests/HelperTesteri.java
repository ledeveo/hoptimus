package fi.hoptimus.olutseuraa.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.bean.TapahtumaImpl;
import fi.hoptimus.olutseuraa.helper.Helpperi;

public class HelperTesteri {

	@Test
	public void testaaTapahtumaLista() {
		
		//luo lista tapahtumia
		List<Tapahtuma> tapahtumat = new ArrayList<Tapahtuma>();
		
		//aseta tapahtumille id:t
		Tapahtuma t1 = new TapahtumaImpl();
		t1.setId(1);
		Tapahtuma t2 = new TapahtumaImpl();
		t2.setId(2);
		Tapahtuma t3 = new TapahtumaImpl();
		t3.setId(1);
		
		//lisää listaan tapahtumat
		tapahtumat.add(t1);
		tapahtumat.add(t2);
		tapahtumat.add(t3);
		

		System.out.println("ennen duplikaattien poistoa lista: " + tapahtumat);
		
		
		//poista listasta duplikaatit
		tapahtumat = Helpperi.PoistaListastaDuplikaatit(tapahtumat);

		System.out.println("duplikaattien poiston jälkeen lista: " + tapahtumat);
		System.out.println(tapahtumat.get(0).getId());
		
		//testaa tapahtumat
		assertEquals(tapahtumat.get(0).getId(), 1);
		assertEquals(tapahtumat.get(1).getId(), 2);
		assertEquals(tapahtumat.get(0).getOsallistujamaara(), 2);
		assertEquals(tapahtumat.get(1).getOsallistujamaara(), 1);
		
		
	}
}
