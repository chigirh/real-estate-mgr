package com.chigirh.eh.rem.security.config;

import com.chigirh.eh.rem.domain.common.ReMgrContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ReMgrContext reMgrContext;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .formLogin()
//            .defaultSuccessUrl("/");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("admin").password("{noop}admin").roles("ADMIN", "USER")
//            .and()
//            .withUser("user").password("{noop}user").roles("USER");
//
//
////        var inMemory = auth.inMemoryAuthentication();
////        reMgrContext.getUsers().forEach(inMemory::withUser);
//    }

}
