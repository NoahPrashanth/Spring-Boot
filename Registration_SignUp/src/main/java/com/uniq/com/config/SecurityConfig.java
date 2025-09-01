package com.uniq.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uniq.com.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private EmployeeService service;
	
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	SecurityFilterChain custm(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable())
			.authorizeHttpRequests(req -> req
					.requestMatchers("/register", "/login").permitAll()
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.formLogin(l -> l.disable());
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authMan(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authPro() {
		DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
		ap.setUserDetailsService(service);
		ap.setPasswordEncoder(encoder());
		return ap;
	}

}
