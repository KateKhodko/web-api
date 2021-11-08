package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.client.MongoGridfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final MongoGridfsClient mongoGridfsClient;

    @Autowired
    public FileController(MongoGridfsClient mongoGridfsClient) {
        this.mongoGridfsClient = mongoGridfsClient;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        return mongoGridfsClient.upload(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("id") String id) throws IOException {
        return mongoGridfsClient.download(id);
    }
}
