package com.adrian.alkemyChallenge.sendgrid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendgridConfig {

	@Value("${sendgrid.key}")
	private String key;
	
	@Bean
	public SendGrid getSengrid()
	{
		return new SendGrid(key);
	}
}