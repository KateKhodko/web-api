package com.khodko.webapi.webapi.client;


import com.khodko.webapi.webapi.config.FeignSupportConfig;
import java.io.IOException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "mongo-gridfs-service", url = "${ext-service.mongo-gridfs-service}", configuration = FeignSupportConfig.class)
public interface MongoGridfsClient {

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException;

  @RequestMapping(method = RequestMethod.GET, value = "/download/{id}")
  ResponseEntity<ByteArrayResource> download(@PathVariable("id") String id) throws IOException;

}
