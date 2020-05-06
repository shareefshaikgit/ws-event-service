package com.shar.event.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtProperties {

  private String secretKey;

  private String tokenPrefix;
}
