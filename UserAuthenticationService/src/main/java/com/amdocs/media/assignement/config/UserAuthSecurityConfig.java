package com.amdocs.media.assignement.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.amdocs.media.assignement.service.UserLoginService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired UserLoginService userLoginService;

	@Autowired
	private PasswordEncoder passwordEncoder;

     
	/*
	 * @Override public void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * 
	 * auth.userDetailsService(userLoginService);
	 * 
	 * }
	 */
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserLoginService();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	
    	  http.authorizeRequests().antMatchers("/login").permitAll().and()
          .authorizeRequests().antMatchers("/h2/**","/getUser","/userlogin").permitAll();
  http.csrf().disable();
  http.headers().frameOptions().disable()
  .and().authorizeRequests()
  .anyRequest()
  .authenticated()
  .and().httpBasic();
  
	/*
	 * http.authorizeRequests() .anyRequest().authenticated() .and()
	 * .formLogin().permitAll() .and() .logout().permitAll();
	 */
    	

    
    }
  

	 	

}



