package com.khodko.webapi.webapi.resttemplate;

import java.io.IOException;
import java.net.URI;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MongoRestTemplate {

  private final RestTemplate restTemplate;

  public MongoRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ResponseEntity<String> upload(MultipartFile file) throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.setCacheControl(CacheControl.noCache());

    MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();

    ContentDisposition contentDisposition = ContentDisposition
        .builder("form-data")
        .name("file")
        .filename(file.getName())
        .build();
    fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

    byte[] clone = file.getBytes().clone();
    HttpEntity<byte[]> fileEntity = new HttpEntity<>(clone, fileMap);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", fileEntity);

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

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
