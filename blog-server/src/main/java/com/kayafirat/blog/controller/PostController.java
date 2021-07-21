package com.kayafirat.blog.controller;

import com.kayafirat.blog.dto.PostDTO;
import com.kayafirat.blog.entity.PostDetail;
import com.kayafirat.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int size,
            @RequestParam(defaultValue = "id",required = false) String sort,
            @RequestParam(defaultValue = "asc",required = false) String order){
        return ResponseEntity.ok(postService.getPosts(page,size,sort,order));
    }

    @GetMapping(value = "search")
    public ResponseEntity<?> getByTitle(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int size,
            @RequestParam(defaultValue = "id",required = false) String sort,
            @RequestParam(defaultValue = "asc",required = false) String order){
        return ResponseEntity.ok(postService.getPostsByTitle(keyword,page,size,sort,order));
    }

    @GetMapping(value = "category")
    public ResponseEntity<?> getAllCategory(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "10",required = false) int size,
            @RequestParam(defaultValue = "id",required = false) String sort,
            @RequestParam(defaultValue = "asc",required = false) String order){
        return ResponseEntity.ok(postService.getPostsByCategoryName(categoryName,page,size,sort,order));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(postService.getDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.addPost(postDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PostDetail postDetail){
        return ResponseEntity.ok(postService.updatePost(postDetail));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
