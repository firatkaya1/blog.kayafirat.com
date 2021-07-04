package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.CategoryDTO;
import com.kayafirat.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    Category get(Long id);
    
    Category addCategory(Category category);
    
    Category updateCategory(Category category);

    List<CategoryDTO> getCategories();


}
