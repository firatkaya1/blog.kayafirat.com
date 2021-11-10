package com.kayafirat.blog.controller;

import com.kayafirat.blog.dto.PostDTO;
import com.kayafirat.blog.entity.PostDetail;
import com.kayafirat.blog.repository.ConfigRepository;
import com.kayafirat.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;
    private final ConfigRepository configrepository;

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int size,
            @RequestParam(defaultValue = "createdDate",required = false) String sort,
            @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(postService.getPosts(page,size,sort,order));
    }

    @GetMapping(value = "search")
    public ResponseEntity<?> getByTitle(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int size,
            @RequestParam(defaultValue = "createdDate",required = false) String sort,
            @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(postService.getPostsByTitle(keyword,page,size,sort,order));
    }

    @GetMapping(value = "category")
    public ResponseEntity<?> getAllCategory(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int size,
            @RequestParam(defaultValue = "createdDate",required = false) String sort,
            @RequestParam(defaultValue = "desc",required = false) String order){
        return ResponseEntity.ok(postService.getPostsByCategoryName(categoryName,page,size,sort,order));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id){
        postService.increasePostView(id);
        return ResponseEntity.ok(postService.getDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.addPost(postDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.updatePost(postDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("config/status")
    public ResponseEntity<?> status(){
        HashMap<String,String> map = new HashMap<>();
        map.put("status",configrepository.findByConfigKod("status"));
        return ResponseEntity.ok(map);
    }

}
