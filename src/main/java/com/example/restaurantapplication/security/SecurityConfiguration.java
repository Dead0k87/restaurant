package com.example.restaurantapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//https://github.com/thymeleaf/thymeleaf-extras-springsecurity/issues/61
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("tom").password(passwordEncoder().encode("cat")).roles("USER")
                .and()
                .withUser("login").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
//                .antMatchers("/summary").hasRole("USER")
//                .antMatchers("/orders_list").hasRole("USER")
//                .antMatchers("/summary/**").hasRole("USER")
//                .antMatchers("/orders_list/**").hasRole("USER")
//                .antMatchers("/**").hasRole("ADMIN")

                .anyRequest()
                .authenticated().and().formLogin()
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }
}
