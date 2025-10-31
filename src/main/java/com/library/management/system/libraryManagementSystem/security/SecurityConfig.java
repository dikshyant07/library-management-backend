package com.library.management.system.libraryManagementSystem.security;

import com.library.management.system.libraryManagementSystem.filters.JwtFiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.library.management.system.libraryManagementSystem.enums.Permission.*;
import static com.library.management.system.libraryManagementSystem.enums.Role.ADMIN;
import static com.library.management.system.libraryManagementSystem.enums.Role.MEMBER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String adminRoute = "/api/v1/admin/**";
    private static final String memberRoute = "/api/v1/member/**";
    private static final String authRoute = "/api/v1/auth/**";
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtFiler jwtFiler;

    @Autowired
    public SecurityConfig(@Qualifier(value = "customAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler, JwtFiler jwtFiler) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtFiler = jwtFiler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(source()))
                .authorizeHttpRequests(req -> req
                        .requestMatchers(authRoute,
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/actuator/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers(adminRoute).hasRole(ADMIN.name())
                        .requestMatchers(GET, adminRoute).hasAnyAuthority(ADMIN_READ.getPermissionName())
                        .requestMatchers(POST, adminRoute).hasAnyAuthority(ADMIN_CREATE.getPermissionName())
                        .requestMatchers(PUT, adminRoute).hasAnyAuthority(ADMIN_UPDATE.getPermissionName())
                        .requestMatchers(DELETE, adminRoute).hasAnyAuthority(ADMIN_DELETE.getPermissionName())

                        .requestMatchers(memberRoute).hasAnyRole(ADMIN.name(), MEMBER.name())

                        .requestMatchers(GET, memberRoute).hasAnyAuthority(ADMIN_READ.getPermissionName(), MEMBER_READ.getPermissionName())
                        .requestMatchers(POST, memberRoute).hasAnyAuthority(ADMIN_CREATE.getPermissionName(), MEMBER_CREATE.getPermissionName())
                        .requestMatchers(PUT, memberRoute).hasAnyAuthority(ADMIN_UPDATE.getPermissionName(), MEMBER_UPDATE.getPermissionName())
                        .requestMatchers(DELETE, memberRoute).hasAnyAuthority(ADMIN_DELETE.getPermissionName(), MEMBER_DELETE.getPermissionName())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtFiler, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, @Qualifier(value = "customUserDetailsService") UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource source() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedOriginPatterns(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return corsConfigurationSource;
        
    }
}














