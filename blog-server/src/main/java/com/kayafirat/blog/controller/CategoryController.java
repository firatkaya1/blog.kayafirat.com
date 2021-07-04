package com.kayafirat.blog.controller;

import com.kayafirat.blog.entity.Category;
import com.kayafirat.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.get(id));
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

}
