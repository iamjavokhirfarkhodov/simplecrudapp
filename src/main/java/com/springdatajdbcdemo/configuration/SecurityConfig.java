package com.springdatajdbcdemo.configuration;

import com.springdatajdbcdemo.authentication.AppUserService;
import com.springdatajdbcdemo.exceptions.AuthException;
import com.springdatajdbcdemo.jwt.JwtTokenVerifier;
import com.springdatajdbcdemo.jwt.UsernameAndPasswordFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private AuthenticationManager manager;
    private AuthException authException;

    public SecurityConfig(AuthenticationManager manager, AuthException authException) {
        this.manager = manager;
        this.authException = authException;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> {
                    exceptions.authenticationEntryPoint(authException);
                })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/signup/**").permitAll();
                    auth.requestMatchers("api/v1/management/**").hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN");
                    auth.requestMatchers("api/v1/students/**").hasAnyAuthority("ROLE_STUDENT");
                    auth.anyRequest().authenticated();
                })
                .addFilter(UsernameAndPasswordFilter.builder().manager(manager).build())
                .addFilterAfter(JwtTokenVerifier.builder().build(), UsernameAndPasswordFilter.class)
                .build();

    }

    // ApplicationContext
    // SecurityContextHolder
    // SecurityContext
    // Authentication
    // AuthenticationManager
    // AuthenticationManager -> ProviderManager -> AuthenticationProvider -
    // AuthenticationProvider - DaoAuthenticationProvider - UserDetailsService
    // User
    // HttpSecurity
    // WebSecurity
}
