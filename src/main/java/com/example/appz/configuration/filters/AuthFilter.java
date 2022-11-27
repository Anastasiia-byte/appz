package com.example.appz.configuration.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/login")
                || request.getRequestURI().equals("/api/register")
                || request.getRequestURI().matches("/chat/.*")
                || request.getRequestURI().matches("/notification/.*")
                || request.getRequestURI().equals("/api/register/info")) {
            doFilter(request, response, filterChain);
            return;
        }

        String tokenStatement = request.getHeader("Authorization");

        if (tokenStatement.equals("null")) {
            doFilter(request, response, filterChain);
            return;
        }

        String[] split = tokenStatement.split(" ");

        UserDetails userDetails = userDetailsService.loadUserByUsername(split[1]);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
