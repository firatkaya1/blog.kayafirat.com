package com.kayafirat.blog.controller;

import com.kayafirat.blog.entity.Contact;
import com.kayafirat.blog.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactController {

    private final ContactService contactService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
        return ResponseEntity.ok(contactService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Contact contact) {
        contactService.save(contact);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.update(contact));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        contactService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/status")
    public ResponseEntity<?> updateReadStatus(@RequestParam Long id) {
        contactService.updateReadStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
