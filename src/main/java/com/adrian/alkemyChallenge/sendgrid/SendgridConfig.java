package com.adrian.alkemyChallenge.sendgrid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendgridConfig {

		
	@Bean
	public SendGrid getSendgrid(){

		return new SendGrid(System.getenv("SENDGRIDKEY"));
	}
}
