package com.github.jstrainer.jstrainerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jstrainer.Strainer;

@Configuration
public class StrainerConfiguration {

	@Bean
	public Strainer strainer() {
		return new Strainer();
	}

}
