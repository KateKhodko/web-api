package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.client.ForesterClient;
import com.khodko.webapi.webapi.dto.ForesterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/forester")
@RestController
public class ForesterController {

    private final ForesterClient foresterClient;

    @Autowired
    public ForesterController(ForesterClient foresterClient) {
        this.foresterClient = foresterClient;
    }

    @PostMapping("/{id}")
    public ForesterDto attachImage(@PathVariable("id") Long foresterId,
                                   @RequestParam("imageId") String imageId) {
        return foresterClient.attachImage(foresterId, imageId);
    }

    @PostMapping
    public ForesterDto createForester(@RequestBody ForesterDto foresterDto) {
        return foresterClient.createForester(foresterDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForesterDto> findForester(@PathVariable("id") Long id) {
        return foresterClient.findForester(id);
    }
}
