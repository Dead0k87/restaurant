package com.example.restaurantapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("tom").password("{noop}cat").roles("USER")
                .and()
                .withUser("login").password("{noop}password").roles("USER", "ADMIN");


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
