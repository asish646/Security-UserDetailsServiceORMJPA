package com.hsbc.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/user/register","/user/save","/user/all", "/user/login").permitAll()
		.antMatchers("/user/common").authenticated()
		.antMatchers("/user/admin").hasAuthority("admin")
		.antMatchers("/user/employee").hasAuthority("employee")
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/user/login")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/user/setUp", true)
		.failureUrl("/user/login?error")
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/user/login?logout")
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/user/denied");
	}

	
}
