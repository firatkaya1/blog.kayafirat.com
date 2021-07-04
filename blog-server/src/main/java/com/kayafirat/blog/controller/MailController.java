package com.kayafirat.blog.controller;

import com.kayafirat.blog.entity.MailQueue;
import com.kayafirat.blog.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailController {

    private final MailService mailService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mailService.getAll());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
        return ResponseEntity.ok(mailService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MailQueue mailQueue) {
        mailService.save(mailQueue);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<?> add(@RequestBody List<MailQueue> mailQueue) {
        mailService.save(mailQueue);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        mailService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
