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
		t2.setId(1);
		Tapahtuma t3 = new TapahtumaImpl();
		t3.setId(1);
		Tapahtuma t4 = new TapahtumaImpl();
		t4.setId(2);
		Tapahtuma t5 = new TapahtumaImpl();
		t5.setId(2);
		Tapahtuma t6 = new TapahtumaImpl();
		t5.setId(3);
		Tapahtuma t7 = new TapahtumaImpl();
		t5.setId(4);
		
		//lisää listaan tapahtumat
		tapahtumat.add(t1);
		tapahtumat.add(t2);
		tapahtumat.add(t3);
		tapahtumat.add(t4);
		tapahtumat.add(t5);
		tapahtumat.add(t6);
		tapahtumat.add(t7);
		

		System.out.println("ennen duplikaattien poistoa lista: " + tapahtumat);
		
		
		//poista listasta duplikaatit
		tapahtumat = Helpperi.PoistaListastaDuplikaatit(tapahtumat);

		
		System.out.println("duplikaattien poiston jälkeen lista: " + tapahtumat);
		System.out.println("tapahtuman 1. id: " + tapahtumat.get(0).getId());
		System.out.println("tapahtuman 2. id: " + tapahtumat.get(1).getId());
		System.out.println("tapahtuman 3. id: " + tapahtumat.get(2).getId());
		System.out.println("tapahtuman 4. id: " + tapahtumat.get(3).getId());
		
		System.out.println("tapahtumaan 1. osallistuu: " + tapahtumat.get(0).getOsallistujamaara());
		System.out.println("tapahtumaan 2. osallistuu: " + tapahtumat.get(1).getOsallistujamaara());
		System.out.println("tapahtumaan 3. osallistuu: " + tapahtumat.get(2).getOsallistujamaara());
		System.out.println("tapahtumaan 4. osallistuu: " + tapahtumat.get(3).getOsallistujamaara());
		
		//testaa tapahtumat
		assertEquals(tapahtumat.get(0).getId(), 1);
		assertEquals(tapahtumat.get(1).getId(), 2);
		assertEquals(tapahtumat.get(2).getId(), 4);
		assertEquals(tapahtumat.get(3).getId(), 0);
		assertEquals(tapahtumat.get(0).getOsallistujamaara(), 3);
		assertEquals(tapahtumat.get(1).getOsallistujamaara(), 1);
		assertEquals(tapahtumat.get(2).getOsallistujamaara(), 1);
		assertEquals(tapahtumat.get(3).getOsallistujamaara(), 2);
		
		
	}
}
