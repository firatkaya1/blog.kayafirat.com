package com.kayafirat.blog.controller;


import com.kayafirat.blog.entity.Notification;
import com.kayafirat.blog.entity.NotificationPermission;
import com.kayafirat.blog.service.NotificationService;
import com.kayafirat.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {

    private final UserService userService;
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> getNotifications(@RequestParam(defaultValue = "1",required = false) int page,
                                              @RequestParam(defaultValue = "10",required = false) int size,
                                              @RequestParam(defaultValue = "created_date",required = false) String sort,
                                              @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(notificationService.getNotifications(page,size,sort,order));
    }

    @GetMapping(value = "/permissions")
    public ResponseEntity<?> getUserNotificationPermissions(){
        return ResponseEntity.ok(userService.getUserNotificationPermissions(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @PostMapping(value = "/permissions")
    public ResponseEntity<?> setUserNotificationPermissions(@RequestBody NotificationPermission notificationPermission){
        return ResponseEntity.ok(notificationService.updateNotificationPermissions(notificationPermission,Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @PostMapping
    public ResponseEntity<?> addNotification(@RequestBody Notification notification){
        notificationService.addNotification(notification);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/read/{id}")
    public ResponseEntity<?> readNotification(@PathVariable Long id){
        notificationService.read(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/read")
    public ResponseEntity<?> readNotifications(){
        notificationService.allRead();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "has")
    public ResponseEntity<?> hasNotification(){
        return ResponseEntity.ok(notificationService.hasNotification(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllNotifications(){
        notificationService.deleteAllNotification();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
