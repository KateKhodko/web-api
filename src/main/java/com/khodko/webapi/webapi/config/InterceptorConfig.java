package com.khodko.webapi.webapi.config;

import com.khodko.webapi.webapi.service.kafka.HistoryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final HistoryInterceptor historyInterceptor;

    @Autowired
    public InterceptorConfig(HistoryInterceptor historyInterceptor) {
        this.historyInterceptor = historyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(historyInterceptor);
    }
}
