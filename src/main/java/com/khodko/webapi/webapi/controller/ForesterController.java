package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.dto.ForesterDto;
import com.khodko.webapi.webapi.resttemplate.ForesterRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/forester")
@RestController
public class ForesterController {

    private final ForesterRestTemplate foresterRestTemplate;

    @Autowired
    public ForesterController(ForesterRestTemplate foresterRestTemplate) {
        this.foresterRestTemplate = foresterRestTemplate;
    }


    @PostMapping("/{id}")
    public ForesterDto attachImage(@PathVariable("id") Long foresterId,
                                   @RequestParam("imageId") String imageId) {
        return foresterRestTemplate.attachImage(foresterId, imageId);
    }


    @PostMapping
    public ForesterDto createForester(@RequestBody ForesterDto foresterDto) {
        return foresterRestTemplate.createForester(foresterDto);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<ForesterDto> findForester(@PathVariable("id") Long id) {
        return foresterClient.findForester(id);
    }

     */
}
