package com.tech2java.springsecurity.springsecurity.projectconfig;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("=====Inside CustomAuthenticationProvider::authenticate() method==========");
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();

        if("test".equals(username) && "12345".equals(password)) {
            //last parameter is authorities
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        }else{
            throw  new BadCredentialsException("Invalid username or password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("=====Inside CustomAuthenticationProvider::supports() method==========");
        //AuthenticationManager passes UsernamePasswordAuthenticationToken in the authentication and to see whether
        //CustomAuthenticationProvider supports UsernamePasswordAuthenticationToken or not.
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
