package com.adrian.alkemyChallenge.sendgrid.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.alkemyChallenge.sendgrid.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


@Service
public class EmailService {

	@Autowired
	SendGrid sendgrid;

	
	
	public Response sendemail(EmailRequest emailRequest) {
		
		 Mail mail = new Mail(new Email("adriver010@gmail.com"), emailRequest.getSubject(), new Email(emailRequest.getTo()), new Content("text/plain", emailRequest.getBody()));
		 mail.setReplyTo(new Email("adriver010@gmail.com"));
		 Request request = new Request();

		 Response response = null;
		 
		 try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      
		      response=this.sendgrid.api(request);
		 } catch (IOException ex) {
		    	
			  System.out.println(ex.getMessage());
		      //throw ex;
		 }		
		
		return response;
		
	}	
	
}
