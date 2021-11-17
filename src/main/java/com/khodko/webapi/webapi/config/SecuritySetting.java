package com.khodko.webapi.webapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecuritySetting {

  @Value("${security.white-list}")
  private String[] WHITE_LIST;
}
