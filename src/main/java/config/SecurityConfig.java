package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
 
	//DelegatingPasswordEncoder 
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("user").password(encoder.encode("user")).roles("USER").and()
          .withUser("admin").password(encoder.encode("admin")).roles("USER","ADMIN");
        
        System.out.println("HIER");
    }
    
	//NoOpPasswordEncoder
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // enable in memory based authentication with a user named
                // "user" and "admin"
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER").and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http
                .authorizeRequests()
                .antMatchers("/*").hasRole("USER");
        System.out.println("HIER2");
    }
}