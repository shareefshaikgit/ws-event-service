package com.shar.event.filter;

import com.google.common.base.Strings;
import com.shar.event.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class JwtTokenVerifier implements WebFilter {

  private final JwtProperties jwtProperties;

  @Override
  public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

    HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
    log.info("Headers : {}", headers);
    String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
    if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
      return webFilterChain.filter(serverWebExchange);
    }
    try {
      String token = authorizationHeader.replace(jwtProperties.getTokenPrefix() + " ", "");
      String secretKey = jwtProperties.getSecretKey();
      Jws<Claims> claimsJws =
          Jwts.parser()
              .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
              .parseClaimsJws(token);
      Claims body = claimsJws.getBody();
      String username = body.getSubject();
      var authorities = (List<Map<String, String>>) body.get("authorities");
      log.info("authorities : {}", authorities
      );
      Set<SimpleGrantedAuthority> authority =
          authorities.stream()
              .map(m -> new SimpleGrantedAuthority(m.get("authority")))
              .collect(Collectors.toSet());

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(username, null, authority);
      //      ReactiveSecurityContextHolder.
      //      ReactiveSecurityContextHolder.getContext()
      //          .map(
      //              securityContext -> {
      //                securityContext.setAuthentication(authentication);
      //                return securityContext;
      //              });
      //      SecurityContextHolder.getContext().setAuthentication(authentication);
      //      ReactiveSecurityContextHolder.getContext().log().subscribe();
      //      Authentication authentication1 =
      // SecurityContextHolder.getContext().getAuthentication();
      //      System.out.println("authentication1 = " + authentication1);
      return webFilterChain
          .filter(serverWebExchange)
          .subscriberContext(
              (c) -> {
                return c.hasKey(SecurityContext.class)
                    ? c
                    : c.putAll(
                        Mono.just(new SecurityContextImpl(authentication))
                            .as(ReactiveSecurityContextHolder::withSecurityContext));
              });

    } catch (JwtException e) {
      throw new IllegalStateException("Token Cannot be trusted");
    }
  }
}
