package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.CategoryDTO;
import com.kayafirat.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT id as categoryId, name as categoryName,color as categoryColor from category",nativeQuery = true)
    List<CategoryDTO> findAllCategories();
}
