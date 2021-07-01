package com.txj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @description: Security配置
 * @author: txj
 * @email: catchtom@qq.com
 * @date: 2021/4/4 21:01
 */
//@Configuration(proxyBeanMethods = false)
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    public final static String[] URLS = {"login.html","error.html"};
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(URLS).permitAll()//白名单放行
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().csrf().disable()
//                .formLogin().permitAll();
//    }
//
//
//}
