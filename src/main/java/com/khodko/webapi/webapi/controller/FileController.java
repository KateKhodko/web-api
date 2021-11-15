package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.resttemplate.MongoRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final MongoRestTemplate mongoRestTemplate;

    @Autowired
    public FileController(MongoRestTemplate mongoRestTemplate) {
        this.mongoRestTemplate = mongoRestTemplate;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        return mongoRestTemplate.upload(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("id") String id) throws IOException {
        return mongoRestTemplate.download(id);
    }
}
