package com.khodko.webapi.webapi.resttemplate;

import com.khodko.webapi.webapi.dto.ForesterDto;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ForesterRestTemplate {

    public ForesterDto createForester(ForesterDto foresterDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ForesterDto> request = new HttpEntity<>(foresterDto);
        String uploadResourceUrl = "http://localhost:8094/forester";
        return restTemplate.postForObject(uploadResourceUrl, request, ForesterDto.class);
    }

    public ForesterDto attachImage(Long foresterId, String imageId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(imageId);
        String uploadResourceUrl = "http://localhost:8094/forester/";
        return restTemplate.postForObject(uploadResourceUrl + foresterId, request, ForesterDto.class);
    }
}
