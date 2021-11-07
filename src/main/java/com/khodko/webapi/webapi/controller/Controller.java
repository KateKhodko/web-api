package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.client.MongoGridfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Controller {

    private final MongoGridfsClient mongoGridfsClient;

    @Autowired
    public Controller(MongoGridfsClient mongoGridfsClient) {
        this.mongoGridfsClient = mongoGridfsClient;
    }

    @PostMapping("feign/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return mongoGridfsClient.upload(file);
    }

    @GetMapping("feign/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        return mongoGridfsClient.download(id);
    }
}
