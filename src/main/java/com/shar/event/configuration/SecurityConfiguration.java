package com.shar.event.configuration;


import com.shar.event.filter.JwtTokenVerifier;
import com.shar.event.properties.JwtProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
    private final JwtProperties jwtProperties;

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .addFilterAt(new JwtTokenVerifier(jwtProperties), SecurityWebFiltersOrder.REACTOR_CONTEXT)
        .authorizeExchange()
//        .pathMatchers("/api/auth/login")
//        .permitAll()
        .anyExchange()
        .authenticated();
    return http.build();
  }
}
