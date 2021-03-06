package com.kayafirat.blog.controller;

import com.kayafirat.blog.dto.*;
import com.kayafirat.blog.entity.Comment;
import com.kayafirat.blog.entity.Config;
import com.kayafirat.blog.entity.PostDetail;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;
    private final PostService postService;
    private final NotificationService notificationService;
    private final MailService mailService;
    private final CommentService commentService;
    private final LogService logService;
    private final ConfigService configService;

    private final CacheManager cacheManager;

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

    @GetMapping(value = "topic/list/detail")
    public ResponseEntity<?> getTopicListdetail(){
        return ResponseEntity.ok(postService.getPostsAllDetail());
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
    public ResponseEntity<?> updateTopic(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.updatePost(postDTO));
    }

    @DeleteMapping(value = "topic")
    public ResponseEntity<?> deleteTopic(@RequestParam Long id){
        postService.deletePost(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "topic/hide")
    public ResponseEntity<?> hideTopic(@RequestParam Long id){
        postService.hidePost(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "notification/list")
    public ResponseEntity<?> getNotificationList(@RequestParam(defaultValue = "1",required = false) int page,
                                                 @RequestParam(defaultValue = "100",required = false) int size,
                                                 @RequestParam(defaultValue = "created_date",required = false) String sort,
                                                 @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(notificationService.getAllNotification(page,size,sort,order));
    }

    @PostMapping(value = "notification")
    public ResponseEntity<?> saveNotification(@RequestBody NotificationSaveDTO notificationSaveDTO){
        notificationService.addNotification(notificationSaveDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "mail")
    public ResponseEntity<?> saveMail(@RequestBody MailDTO mailDTO){
        mailService.save(mailDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "mail/list")
    public ResponseEntity<?> getMailList(){
        return ResponseEntity.ok(mailService.getAll());
    }

    @GetMapping(value = "comment/list")
    public ResponseEntity<?> getAllComments(@RequestParam(defaultValue = "1",required = false) int page,
                                            @RequestParam(defaultValue = "100",required = false) int size,
                                            @RequestParam(defaultValue = "commentCreatedDate",required = false) String sort,
                                            @RequestParam(defaultValue = "desc",required = false) String order) {
        return ResponseEntity.ok(commentService.getAllCommentsAdmin(page,size,sort,order));
    }

    @PutMapping(value = "comment")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping(value = "log/list")
    public ResponseEntity<?> getLogList(){
        return ResponseEntity.ok(logService.getLogs());
    }

    @PostMapping("config/cache")
    public ResponseEntity<?> clearCache(){
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("config")
    public ResponseEntity<?> saveConfig(@RequestBody Config config){
        return ResponseEntity.ok(configService.updateConfig(config));
    }



}
