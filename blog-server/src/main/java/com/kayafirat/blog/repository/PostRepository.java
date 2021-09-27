package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.PostViewDTO;
import com.kayafirat.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "SELECT id,title from post order by created_date ASC",nativeQuery = true)
    List<PostViewDTO> findAllPosts();

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContainingIgnoreCase(String title,Pageable pageable);

    Page<Post> findPostByCategoryName(String name,Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE post SET view = view + 1 WHERE id = :postId",nativeQuery = true)
    void increaseView(@Param("postId") Long postId);


}
