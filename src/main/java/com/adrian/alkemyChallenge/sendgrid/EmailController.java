package com.adrian.alkemyChallenge.sendgrid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adrian.alkemyChallenge.sendgrid.service.EmailService;
import com.sendgrid.Response;

@Controller
public class EmailController {

	
	@Autowired
	private EmailService emailService;
	 
	@PostMapping("/sendemail")
	public ResponseEntity<String> sendemail(@RequestBody EmailRequest emailRequest) throws IOException{
		
		Response response= emailService.sendemail(emailRequest);
		if(response.getStatusCode()==200||response.getStatusCode()==202) {
			return new ResponseEntity<>("send succesfully",HttpStatus.OK);
		}	
		return new ResponseEntity<>("failed to sent", HttpStatus.NOT_FOUND);
	
		
	}
}
