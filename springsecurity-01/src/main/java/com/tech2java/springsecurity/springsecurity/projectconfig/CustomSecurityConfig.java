package com.tech2java.springsecurity.springsecurity.projectconfig;

import com.tech2java.springsecurity.springsecurity.filter.CustomSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class CustomSecurityConfig {

    //Before 3.2 version
    /*@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.authorizeHttpRequests().anyRequest().authenticated();
        return  http.build();
    }*/


    //CustomUserDetails

    //Comment if we use Custom Authentication Provider.
    /*@Bean
    UserDetailsService userDetailsService(){

        System.out.println("========Creating UserDetailsService==========");
        //It's default Implementation for UserDetailsService
        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();

        //Create UserDetails with username and password
        //every user has at least one authority

        //UserDetails userDetails =User.withUsername("test").password("12345").authorities("read").build();
        UserDetails userDetails =User.withUsername("test").password(passwordEncoder().encode("12345")).authorities("read").build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return inMemoryUserDetailsManager;
    }*/

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        System.out.println("=========Inside passwordEncoder() method========");
        return new BCryptPasswordEncoder();
    }

    //After 3.2 version
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("================Creating SecurityFilterChain Object.=================");
        //http.httpBasic(Customizer.withDefaults());
        //form based Authentication
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        //http.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
        //Use requestMatcher()
        http.authorizeHttpRequests(authorize->authorize.requestMatchers("/welcome").authenticated()
                //deny all other requests
                /*.anyRequest().denyAll()*///optional
        );
        http.addFilterBefore(new CustomSecurityFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
