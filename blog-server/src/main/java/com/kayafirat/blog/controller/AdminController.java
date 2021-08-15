package com.kayafirat.blog.controller;

import com.kayafirat.blog.dto.PostDTO;
import com.kayafirat.blog.dto.Register;
import com.kayafirat.blog.entity.PostDetail;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.service.PostService;
import com.kayafirat.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping(value = "user/list")
    public ResponseEntity<?> getUserList(){
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping(value = "user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping(value = "user")
    public ResponseEntity<?> saveUser(@RequestBody Register register){
        userService.saveUser(register);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "user")
    public ResponseEntity<?> updateUser(@RequestBody  User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /** Topics  **/

    @GetMapping(value = "topic/list")
    public ResponseEntity<?> getTopicList(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping(value = "topic/{postId}")
    public ResponseEntity<?> getTopic(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getDetail(postId));
    }

    @PostMapping(value = "topic")
    public ResponseEntity<?> saveTopic(@RequestBody PostDTO postDTO){
        postService.addPost(postDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "topic")
    public ResponseEntity<?> updateTopic(@RequestBody PostDetail postDetail){
        return ResponseEntity.ok(postService.updatePost(postDetail));
    }

    @DeleteMapping(value = "topic/{postId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
