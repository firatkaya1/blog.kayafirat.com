package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {

    Optional<PostDetail> findById(Long id);

    Optional<PostDetail> findPostDetailByPostId(Long id);


}