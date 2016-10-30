package fi.hoptimus.olutseuraa.mail;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class EmailTesteri {

	@SuppressWarnings("resource")
	public static void main(String args[]) {
 
		// Spring Bean file you specified in /src/main/resources folder
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-email-config.xml");
 
		SimpleMailMessage mail = new SimpleMailMessage();
		// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
		MailSender mailer = (MailSender) context.getBean("mailSender");

		mail.setFrom("testimeilihoptimus@gmail.com");
		mail.setTo("testimeilihoptimus@gmail.com");
		mail.setSubject("Hei! Tämä on automaattinen viesti, jonka testimeileri lähetti!");
		mail.setText("Tähän viestiin on turha vastata. hihi :D - Hoptimus Team.");
		
		mailer.send(mail);
		
	}
	
}
