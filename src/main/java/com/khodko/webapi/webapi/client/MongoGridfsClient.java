package com.khodko.webapi.webapi.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "mongo-gridfs-service", url = "http://localhost:8092/")
public interface MongoGridfsClient {

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException;

    @RequestMapping(method = RequestMethod.GET, value = "/download/{id}")
    ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException;
}
