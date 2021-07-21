package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.CategoryDTO;
import com.kayafirat.blog.entity.Category;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService implements com.kayafirat.blog.service.CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category bulunamadı"));
    }

    @Override
    public Category addCategory(Category _category) {
        Category category = new Category();
        category.setName(_category.getName());
        category.setColor(_category.getColor());
        category.setTextColor(_category.getTextColor());

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category _category) {
        Category category = categoryRepository.findById(_category.getId()).orElseThrow(() -> new EntityNotFoundException("Category bulunamadı"));

        category.setName(_category.getName());
        category.setColor(_category.getColor());
        category.setTextColor(_category.getTextColor());
        return category;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAllCategories();
    }
}
