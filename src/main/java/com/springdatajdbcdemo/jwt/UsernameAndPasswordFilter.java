package com.springdatajdbcdemo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdatajdbcdemo.dto.UsernameAndPassword;
import com.springdatajdbcdemo.util.ResultMessage;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MINUTES;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager manager;


    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/signup/student");
        if (antPathRequestMatcher.matches(request)){
            return false;
        }
        return super.requiresAuthentication(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPassword usernameAndPassword = new ObjectMapper().readValue(request.getInputStream().readAllBytes(), UsernameAndPassword.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usernameAndPassword.getUsername(),
                    usernameAndPassword.getPassword());
            return manager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException("Username and Password not provided!");
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("plain/text");
        response.getWriter().write("Wrong username or password! User not found!");
        response.getWriter().close();
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String secretKey = "secretKeysecretKeysecretKeysecretKeysecretKeysecretKey";
        String token = Jwts.builder()
                .subject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(60, MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
        try {
            response.getWriter().write("Bearer " + token);
            response.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
