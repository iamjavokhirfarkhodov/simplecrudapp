package com.springdatajdbcdemo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith("/signup/student");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Skipping JWT Authentication for public urls
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null){
//            filterChain.doFilter(request, response);
//            return;
//        }

        // JWT token generation for any protected urls
        String token = request.getHeader("Authorization");
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String payload = token.replace("Bearer ", "");
        String secretKey = "secretKeysecretKeysecretKeysecretKeysecretKeysecretKey";
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(payload);
            Claims body = claimsJws.getPayload();
            String subject = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> roles = authorities.stream().map(stringStringMap -> new SimpleGrantedAuthority(stringStringMap.get("authority"))).collect(Collectors.toSet());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(subject, null, roles);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }
    }
}
