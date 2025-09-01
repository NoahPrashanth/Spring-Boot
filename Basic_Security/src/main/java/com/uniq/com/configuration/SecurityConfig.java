package com.uniq.com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain custEndPoints(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests( req -> req
				.requestMatchers("/loging").permitAll()
				.anyRequest().authenticated())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	@Bean
	InMemoryUserDetailsManager inDB() {
		UserDetails u1 = User.withDefaultPasswordEncoder()
		.username("Noah")
		.password("Noah@1234")
		.build();
		
		UserDetails u2 = User.withDefaultPasswordEncoder()
				.username("Krish")
				.password("Krish@1234")
				.build();
		
		UserDetails u3 = User.withDefaultPasswordEncoder()
				.username("Naveen")
				.password("Naveen@1234")
				.build();
		
		return new InMemoryUserDetailsManager(u1, u2, u3);
	}

}
