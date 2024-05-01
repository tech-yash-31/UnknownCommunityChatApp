package com.chatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.chatapp.filter.JwtFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import com.chatapp.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JwtFilter jwtFilter;
    
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager() ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/register", "/authenticate","/user/{userId}","/getAllUsers","/usersinfo","/create/newgroup","/show/activeGrups",
                											"/groups/{groupId}","/users/{userId}/groups/{groupId}","/specGroupinfo/{groupId}","/ws","/chat/{communityName}","/topic/send/{communityName}",
                											"/chat/{communityName}/sendmessage/*", "/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/** ","/favicon.ico","/manage/**"))
                .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/register", "/authenticate","/user/{userId}","/getAllUsers","/usersinfo",
                                					"/create/newgroup","/show/activeGrups","/groups/{groupId}","/users/{userId}/groups/{groupId}","/specGroupinfo/{groupId}","/ws","/chat/{communityName}",
                                					"/topic/send/{communityName}","/chat/{communityName}/sendmessage/*", "/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/** ","/favicon.ico","/manage/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


