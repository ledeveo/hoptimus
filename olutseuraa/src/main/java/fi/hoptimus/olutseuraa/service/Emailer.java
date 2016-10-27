package fi.hoptimus.olutseuraa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emaileri")
public class Emailer {

	@Autowired
	private MailSender sender; // MailSender interface defines a strategy
										// for sending simple mails
 
	public void send(String toAddress, String fromAddress, String subject, String msgBody) {
 
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(fromAddress);
		mail.setTo(toAddress);
		mail.setSubject(subject);
		mail.setText(msgBody);
		sender.send(mail);
	}
	
}
