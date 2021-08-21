package com.kayafirat.blog.controller;

import com.kayafirat.blog.service.impl.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private final ImageService imageService;

    @GetMapping()
    public ResponseEntity<?> getImageList(){
        return ResponseEntity.ok(imageService.getList());
    }

    @GetMapping(value = "{imageId}")
    public ResponseEntity<?> getImage(@PathVariable Long imageId){
        return ResponseEntity.ok(imageService.getImage(imageId));
    }

    @PostMapping(value = "post")
    public ResponseEntity<?> saveImage(@RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(imageService.saveImage(multipartFile));
    }


}
