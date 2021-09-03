package com.adrian.alkemyChallenge.sendgrid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendgridConfig {

		
	@Bean
	public SendGrid getSengrid(){
		SendGrid sg = new SendGrid(System.getenv("SENDGRIDKEY"));
		
		return sg;
	}
}
