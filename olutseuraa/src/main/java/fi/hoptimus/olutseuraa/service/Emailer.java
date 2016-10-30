package fi.hoptimus.olutseuraa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class Emailer implements MailSender{

	@Autowired
	private MailSender sender; // MailSender interface defines a strategy
							   // for sending simple mails
		
	public void sendMail(String toAddress, String fromAddress, String subject, String msgBody) {
 
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(fromAddress);
		mail.setTo(toAddress);
		mail.setSubject(subject);
		mail.setText(msgBody);
		sender.send(mail);
	}

	public void send(SimpleMailMessage simpleMessage) throws MailException {
		// TODO Auto-generated method stub
		
	}

	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		// TODO Auto-generated method stub
		
	}
	
}
