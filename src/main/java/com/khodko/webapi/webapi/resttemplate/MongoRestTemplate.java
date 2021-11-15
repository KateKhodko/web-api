package com.khodko.webapi.webapi.resttemplate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Component
public class MongoRestTemplate {

    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        String uploadResourceUrl = "http://localhost:8092/upload";
        return restTemplate.postForEntity(uploadResourceUrl, requestEntity, String.class);
    }

    public ResponseEntity<ByteArrayResource> download(String id) {
        RestTemplate restTemplate = new RestTemplate();
        URI downloadResourceUrl = URI.create("http://localhost:8092/download");
        ByteArrayResource resource = restTemplate.getForObject(downloadResourceUrl + id, ByteArrayResource.class);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
