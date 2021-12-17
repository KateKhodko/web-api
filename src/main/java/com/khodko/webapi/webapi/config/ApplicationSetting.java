package com.khodko.webapi.webapi.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationSetting {

    private final String[] WHITE_LIST = {
            "/",
            "/forester/{id}",
            "/user/register"
    };
}
