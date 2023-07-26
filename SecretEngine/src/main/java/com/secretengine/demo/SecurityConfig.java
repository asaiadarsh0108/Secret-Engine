package com.secretengine.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.secretengine.demo.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserService userService;
	@Bean("securityUserService")
	    public UserService userService() {
	        return new UserService();
	    }
	
	private AuthenticationProvider getProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userService());
		return dao;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("msdhoni@incedo.com").password(getEncoder().encode("csk")).authorities("ADMIN")
//			.and()
//			.withUser("vkohli@incedo.com").password(getEncoder().encode("rcb")).authorities("USER");
		auth.authenticationProvider(getProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers(HttpMethod.POST).hasRole("user")
			.anyRequest().permitAll()
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
	}
	
	@Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}