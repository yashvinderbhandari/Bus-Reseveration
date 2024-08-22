package org.sm.reservationapi.service;

import org.sm.reservationapi.dto.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ReservationApiMailService {
	@Autowired
	private JavaMailSender mailSender;

	public String sendMail(EmailConfiguration configuration) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(configuration.getToAddress());
		mailMessage.setText(configuration.getText());
		mailMessage.setSubject(configuration.getSubject());
		mailSender.send(mailMessage);
		return "Registration Successfull.Kindly, Verify Your Email Address.";
	}
}
