package email;
//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;

//public class Email {
/**
 * 
 * @param lahettajanGoogleEmail
 * @param lahettajanGoogleSalasana
 * @param vastaanottajanEmail
 * @param otsikko
 * @param emailinSisalto
 * public void lahetaSahkoposti(String lahettajanGoogleEmail, String lahettajanGoogleSalasana, String vastaanottajanEmail, String otsikko, String emailinSisalto ) {
		// Asetetaan Stringeihin lähettäjän kredentiaalit
		String from = lahettajanGoogleEmail;
		String pass = lahettajanGoogleSalasana;
		// Sähköpostipalvelin
		String host = "smtp.gmail.com";

		// Haetaan järjestelmän ominaisuudet
		Properties properties = System.getProperties();
		// Asetetaan sähköpostipalvelin
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Haetaan oletus session olio
		Session session = Session.getDefaultInstance(properties);

		try {
			// Luo oletus MimeMessage olio
			MimeMessage message = new MimeMessage(session);

			// Viestin lähettäjän asettaminen (täytyy olla gmail-osoite)
			message.setFrom(new InternetAddress(from));

			// Viestin vastaanottajan asettaminen
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					vastaanottajanEmail));

			// Viestin otsikko eli subject
			message.setSubject(otsikko);

			// Asetetaan itse viestin sisältö
			message.setText(emailinSisalto);

			// Lähetetään viesti
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}	
	 */
 
	
