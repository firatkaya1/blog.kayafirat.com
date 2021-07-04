package com.kayafirat.blog.controller;

import com.kayafirat.blog.entity.Comment;
import com.kayafirat.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1") Long id,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "10",required = false) int size,
            @RequestParam(defaultValue = "total_vote",required = false) String sort,
            @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(commentService.getComments(id,page,size,sort,order));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return ResponseEntity.ok(commentService.get(id));
    }

    @GetMapping(value = "total/{id}")
    public ResponseEntity<?> getTotalComment(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getTotalComment(id));
    }

    @PostMapping(value = "/vote/{id}")
    public ResponseEntity<?> save(@PathVariable Long id){
        return ResponseEntity.ok(commentService.addVote(id));
    }

    @GetMapping(value = "/vote/{id}")
    public ResponseEntity<?> getVotes(@PathVariable Long id,
                                      @RequestParam(defaultValue = "1",required = false) int page,
                                      @RequestParam(defaultValue = "10",required = false) int size,
                                      @RequestParam(defaultValue = "date",required = false) String sort,
                                      @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(commentService.getVotes(id,page,size,sort,order));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.updateComment(comment));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> getUserComments(){
        return ResponseEntity.ok(commentService.getUserComments(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }
}
