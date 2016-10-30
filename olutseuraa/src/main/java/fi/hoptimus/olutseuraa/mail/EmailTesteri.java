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
 
		
		MailSender mailer = (MailSender) context.getBean("mailSender");
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom("testimeilihoptimus@gmail.com");
		mail.setTo("testimeilihoptimus@gmail.com");
		mail.setSubject("Hei! Tämä on automaattinen TESTI-viesti, jonka EmailTesteri lähetti!");
		mail.setText("Tähän viestiin on turha vastata. hihi :D - Hoptimus Team.");
		
		mailer.send(mail);
		
	}
	
}
