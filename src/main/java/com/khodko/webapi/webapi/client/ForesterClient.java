package com.khodko.webapi.webapi.client;

import com.khodko.webapi.webapi.config.FeignSupportConfig;
import com.khodko.webapi.webapi.dto.ForesterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "forester-service", url = "http://localhost:8094/forester", configuration = FeignSupportConfig.class)
public interface ForesterClient {

    @GetMapping("/{id}")
    ResponseEntity<ForesterDto> findForester(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ForesterDto attachImage(@PathVariable("id") Long foresterId, @RequestParam("image-id") String imageId);

    @PostMapping
    ForesterDto createForester(@RequestBody ForesterDto foresterDto);
}
