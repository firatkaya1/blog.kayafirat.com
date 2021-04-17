package com.kayafirat.blog.controller;

import com.kayafirat.blog.dto.AuthenticateRequest;
import com.kayafirat.blog.dto.Register;
import com.kayafirat.blog.dto.UserProfileDTO;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.entity.UserPermission;
import com.kayafirat.blog.exception.custom.UnAuthorizedUserException;
import com.kayafirat.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/auth")
    public ResponseEntity<?> isAuthenticate(){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "profile")
    public ResponseEntity<?> getUserProfile(){
        return ResponseEntity.ok(userService.getUserProfile(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @PostMapping(value = "profile")
    public ResponseEntity<?> setUserProfile(@RequestBody UserProfileDTO userProfile){
        userService.setUserProfile(userProfile,Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "permissions/profile")
    public ResponseEntity<?> getUserProfilePermission(){
        return ResponseEntity.ok(userService.getUserPermissions(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @PostMapping(value = "permissions/profile")
    public ResponseEntity<?> setUserProfilePermission(@RequestBody UserPermission userPermission){
        return ResponseEntity.ok(userService.setUserPermissions(userPermission,Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Register register){
        userService.saveUser(register);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticateRequest authenticateRequest, HttpServletResponse response) throws Exception {
        String token = userService.login(authenticateRequest);
        Cookie cookie = new Cookie("authenticate",token);
        cookie.setMaxAge(36000);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        userService.deleteUser(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/photo")
    public ResponseEntity<?> updatePicture(@RequestParam("file") MultipartFile file){
        userService.updateUserImage(file, Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "logout")
    public ResponseEntity<?> logout(HttpServletResponse response)   {
        Cookie cookie = new Cookie("authenticate","");
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}