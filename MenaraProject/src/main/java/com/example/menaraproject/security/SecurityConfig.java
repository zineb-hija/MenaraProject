package com.example.menaraproject.security;

import com.example.menaraproject.security.jwt.JwtAuthenticationFilter;
import com.example.menaraproject.security.jwt.JwtAutorisationFilter;
import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    @Lazy
    private JwtAutorisationFilter jwtAutorisationFilter;

    @Autowired
    public void setJwtAutorisationFilter( JwtAutorisationFilter jwtAutorisationFilter) {
        this.jwtAutorisationFilter = jwtAutorisationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/rh/").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/rh/**").hasAuthority("RH")
                        .requestMatchers(HttpMethod.GET, "/api/rh/**").hasAuthority("RH")
                        .requestMatchers(HttpMethod.PUT, "/api/rh/**").hasAuthority("RH")
                        .requestMatchers(HttpMethod.DELETE, "/api/rh/**").hasAuthority("RH")
                        //.requestMatchers(HttpMethod.POST, "/api/rh/{rhId}/encadrant").hasAuthority("RH")
                        //.requestMatchers(HttpMethod.GET, "/api/rh/role/{role}").hasAuthority("RH")


                        .requestMatchers(HttpMethod.POST, "/api/encadrant/**").hasAuthority("ENCADRANT")
                        .requestMatchers(HttpMethod.GET, "/api/encadrant/**").hasAuthority("ENCADRANT")
                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
                .addFilterBefore(jwtAutorisationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
