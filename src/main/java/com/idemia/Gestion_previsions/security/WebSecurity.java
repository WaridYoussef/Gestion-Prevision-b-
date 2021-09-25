package com.idemia.Gestion_previsions.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.idemia.Gestion_previsions.services.UserService;






@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
 public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SING_UP_URL)
		.permitAll()
		.antMatchers(  "/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui.html/**",
				"/webjars/**")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
	}
	
	//had lfunction bach n personalisiw l url w hia li 3ayetna 3liha f addfilter f configure(http...)
		protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		    filter.setFilterProcessesUrl("/user/login");
		    return filter;
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}
