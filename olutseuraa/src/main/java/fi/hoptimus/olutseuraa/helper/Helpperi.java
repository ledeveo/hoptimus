package fi.hoptimus.olutseuraa.helper;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;

public class Helpperi {
	
	//poistaa duplikaatit ja yhdist‰‰ osallistujam‰‰r‰t
	public static List<Tapahtuma> PoistaListastaDuplikaatit(List<Tapahtuma> tapahtumat) {
		
		tapahtumat = lisaaOsallistujaMaarat(tapahtumat);
		
		tapahtumat = poistaDuplikaatit(tapahtumat);
		
		return tapahtumat;
	}
	
	private static List<Tapahtuma> poistaDuplikaatit(List<Tapahtuma> tapahtumat) {
		
		List<Tapahtuma> tapahtumat2 = tapahtumat;
		
		//lis‰‰ uuteen listaan tapahtumat ilman duplikaatteja
		for(int i = 0; i < tapahtumat.size(); i++) {
			for(int j = 0; j < tapahtumat2.size(); j++) {
				
				if(j < tapahtumat2.size() && i < tapahtumat.size()) {
					//jos id sama, poista j‰lkimm‰isempi uudesta listasta
					if(tapahtumat.get(i).getId() == tapahtumat2.get(j).getId()) {
						
						//jos olemassa uudessa listassa ja ei sama indeksi
						if(tapahtumat2.get(j) != null && i != j) {
							
							//poista duplikaatti
							tapahtumat2.remove(j);
						}
					}
				}
				
			}
		}
		//jostain syyst‰ t‰ss‰ kohtaa on viel‰ kaksi samaa tapahtumaa, tehr‰‰s luuppi uurelleen
		List<Tapahtuma> tapahtumat3 = tapahtumat2;
		
		//lis‰‰ uuteen listaan tapahtumat ilman duplikaatteja
		for(int i = 0; i < tapahtumat2.size(); i++) {
			for(int j = 0; j < tapahtumat3.size(); j++) {
				
				if(j <= tapahtumat3.size() && i <= tapahtumat2.size()) {
					//jos id sama, poista j‰lkimm‰isempi uudesta listasta
					if(tapahtumat2.get(i).getId() == tapahtumat3.get(j).getId()) {
						
						//jos olemassa uudessa listassa ja ei sama indeksi
						if(tapahtumat3.get(j) != null && i != j) {
							
							//poista duplikaatti
							tapahtumat3.remove(j);
						}
					}
				}
				
			}
		}
		/*//DEBUG
		 * System.out.println("tapahtumat2 sis‰ltˆ:");
		for(int i = 0; i < tapahtumat.size(); i++) {
			System.out.println("tapahtuma id: " + tapahtumat.get(i).getId() + ", osallistujia: " + tapahtumat.get(i).getOsallistujamaara());
		}
		*/
		return tapahtumat2;
	}

	public static List<Tapahtuma> lisaaOsallistujaMaarat(List<Tapahtuma> tapahtumat) {
		
		//lis‰‰ listaan osallistujam‰‰r‰t
		for(int i = 0; i < tapahtumat.size(); i++) {
			for(int j = 0; j < tapahtumat.size(); j++) {
				//jos id sama, nosta osallistujam‰‰r‰‰
				if(tapahtumat.get(i).getId() == tapahtumat.get(j).getId()) {
					tapahtumat.get(i).setOsallistujamaara(tapahtumat.get(i).getOsallistujamaara() + 1);
				}
			}
		}
		return tapahtumat;
	}
	
	public static void lahetaAktivointilinkki(Henkilo henkilo, Tapahtuma t, MailSender mailer) {
		//luodaan simple message
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("testimeilihoptimus@gmail.com");
		mail.setTo(henkilo.getSahkoposti());
		mail.setSubject("Hei " + henkilo.getEtunimi() +"! Aktivoi tunnuksesi olutseuran sivuille!");
		
		//linkki
		String linkki = "http://proto285:8080/olutseuraa/aktivoi" + henkilo.getId(); //protolle ohjaus
		//String linkki  ="http://localhost:8080/olutseuraa/aktivoi" + henkilo.getId(); //localhostilla kikkailua varten
		
		mail.setText("Hei " + henkilo.getEtunimi() + "! Olet osallistunut tapahtumaan " + t.getNimi() + " Olutseuraa-sivuilla." + 
				" Tapahtuma alkaa " + t.getPvm() + " klo " + t.getAika() + " paikassa: " + t.getPaikka() +
				" Mene t‰h‰n linkkiin aktivoidaksesi tunnuksesi: " + linkki + " Samalla vahvistat osallistumisen tapahtumaan. - Hoptimus Team.");
		
		//l‰hetet‰‰n se k‰ytt‰j‰n s‰hkˆpostiin
		mailer.send(mail);
	}
	
	public static void lahetaTapahtumaanLiittyminenOnnistunut(Henkilo henkilo, Tapahtuma t, MailSender mailer) {
		//luodaan simple message
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("testimeilihoptimus@gmail.com");
		mail.setTo(henkilo.getSahkoposti());
		mail.setSubject("Hei " + henkilo.getEtunimi() +"! Olet liittynyt tapahtumaan " + t.getNimi() + "!");
		
		//linkki
		String linkki = "http://proto285:8080/olutseuraa/login"; //protolle ohjaus
		//String linkki  ="http://localhost:8080/olutseuraa/login"; //localhostilla kikkailua varten
		
		mail.setText("Hei " + henkilo.getEtunimi() + "! Olet osallistunut tapahtumaan " + t.getNimi() + " Olutseuraa-sivuilla." +
		" Tapahtuma alkaa " + t.getPvm() + " klo " + t.getAika() + " paikassa: " + t.getPaikka() +
		" Voit tarkistella tapahtumiasi k‰ytt‰j‰sivulla kirjautumalla sis‰‰n: " + linkki + " - Hoptimus Team.");
		
		//l‰hetet‰‰n se k‰ytt‰j‰n s‰hkˆpostiin
		mailer.send(mail);
	}
	
	public static void lahetaAktivointiOnnistunutlinkki(Henkilo henkilo, MailSender mailer) {
		//l‰hetet‰‰n aktivoinnista ilmoitus k‰ytt‰j‰lle s‰hkˆpostiin
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("testimeilihoptimus@gmail.com");
		mail.setTo(henkilo.getSahkoposti());
		mail.setSubject("Hei " + henkilo.getEtunimi() + "! Tilisi on aktivoitu!");
		String linkki = "http://localhost:8080/olutseuraa/login";
		//String linkki = "http//proto285:8080/olutseuraa/login";
		mail.setText("Hei " + henkilo.getEtunimi() + "! Olet aktivoinut onnistuneesti tilisi. P‰‰set katsomaan profiiliasi kirjautumalla Olutseuraa-sivuilla: " + linkki + " - Hoptimus Team.");
		//l‰hetet‰‰n se k‰ytt‰j‰n s‰hkˆpostiin
		mailer.send(mail);
	}
}
