package com.nsa.team10.asgproject.config;

import com.nsa.team10.asgproject.services.implementations.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private final AccountService userDetailsService;

    @Autowired
    public WebSecurityConfiguration(AccountService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("Admin")
                .antMatchers("/instructor/**").hasAuthority("Instructor")
                .antMatchers("/candidate/**").hasAuthority("Candidate")
                .antMatchers("/account/**", "/api/v1/account/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .failureHandler((request, response, exception) ->
                        response.setStatus(HttpStatus.UNAUTHORIZED.value()))
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/account/login");
        http.headers().frameOptions().disable();
        http.headers().contentSecurityPolicy("script-src 'self' 'unsafe-eval' 'unsafe-inline' http://localhost:8080 https://localhost:8443 https://cdnjs.cloudflare.com; style-src 'self' 'unsafe-inline' http://localhost:8080 https://localhost:8443 https://fonts.googleapis.com; img-src 'self' http://localhost:8080 https://localhost:8443;");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }
}
