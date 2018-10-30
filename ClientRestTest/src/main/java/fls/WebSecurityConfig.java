package fls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user"))
        .roles("USER");
  }

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**").antMatchers("/console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/", "/get/userDTO", "/register", "/css/**").permitAll()
        .antMatchers("/manage**").hasAuthority("ADMIN").anyRequest().authenticated().and()
        .formLogin().failureUrl("/login?error").usernameParameter("userName").permitAll().and()
        .logout().invalidateHttpSession(true).logoutSuccessUrl("/").permitAll().and().rememberMe();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @ControllerAdvice
  public class CurrentUserControllerAdvice {
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
      return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
  }

}
