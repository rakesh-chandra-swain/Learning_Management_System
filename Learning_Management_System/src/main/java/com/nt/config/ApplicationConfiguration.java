package com.nt.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Configuration
@EnableWebSecurity
public class ApplicationConfiguration {
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    return http
//	        .sessionManagement(session -> session
//	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/api/**").authenticated()
//	            .anyRequest().permitAll())
//	        .csrf(csrf -> csrf.disable())
//	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//	        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//	        .httpBasic(Customizer.withDefaults())
//	        .formLogin(Customizer.withDefaults())
//	        .build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/api/enrollments/user/{userId}")
						.hasAnyRole("STUDENT", "ADMIN")
						.requestMatchers("/api/quizzes/{quizId}/questions").hasRole("ADMIN")
						.requestMatchers("/api/enrollments/enroll","/api/quizzes","/api/progress/update").hasRole("STUDENT")
				
			        
						
						.anyRequest().permitAll())
						
//				.exceptionHandling(exception -> exception
//					    .authenticationEntryPoint(customAuthEntryPoint())
//					    .accessDeniedHandler(customAccessDeniedHandler()) // ✅ custom handler
					

						
						
				.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults()).build();
	}

	  

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        // Configure CORS settings here
	        return request -> {
	            var config = new org.springframework.web.cors.CorsConfiguration();
	            config.addAllowedOrigin("http://localhost:5173"); // Allow all origins
	            config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc.)
	            config.addAllowedHeader("*"); // Allow all headers
	            config.setAllowCredentials(true);
	            return config;
	        };
	}
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    // AuthenticationManager bean
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
//	    
//	    @Bean
//	    public AccessDeniedHandler customAccessDeniedHandler() {
//	        return new AccessDeniedHandler() {
//	            @Override
//	            public void handle(HttpServletRequest request,
//	                               HttpServletResponse response,
//	                               AccessDeniedException accessDeniedException) throws IOException {
//	                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//	                response.setContentType("application/json");
//	                response.getWriter().write("""
//	                    {
//	                      "error": "❌ Access Denied",
//	                      "message": "Only ADMIN can access this resource",
//	                      "path": "%s"
//	                    }
//	                    """.formatted(request.getRequestURI()));
//	            }
//	        };
//	    }
//	    @Bean
//	    public AuthenticationEntryPoint customAuthEntryPoint() {
//	        return (request, response, authException) -> {
//	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	            response.setContentType("application/json");
//	            response.getWriter().write("""
//	                {
//	                  "error": "Unauthorized",
//	                  "message": "Invalid email or password, please go to forgot password",
//	                  "path": "%s"
//	                }
//	                """.formatted(request.getRequestURI()));
//	        };
//	    }



}
