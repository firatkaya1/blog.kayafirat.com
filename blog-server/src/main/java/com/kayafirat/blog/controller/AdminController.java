package com.kayafirat.blog.controller;

import com.kayafirat.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;

    @GetMapping(value = "user/list")
    public ResponseEntity<?> getUserList(){
        return ResponseEntity.ok(userService.getUserList());
    }

}
