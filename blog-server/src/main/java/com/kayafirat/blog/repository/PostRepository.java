package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.PostViewDTO;
import com.kayafirat.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "SELECT id,title from post",nativeQuery = true)
    List<PostViewDTO> findAllPosts();

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContainingIgnoreCase(String title,Pageable pageable);

    Page<Post> findPostByCategoryName(String name,Pageable pageable);

}
