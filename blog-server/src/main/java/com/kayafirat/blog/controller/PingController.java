package com.kayafirat.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PingController {

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("PONG");
    }

}
