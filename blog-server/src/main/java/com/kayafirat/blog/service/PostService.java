package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.PostDTO;
import com.kayafirat.blog.dto.PostViewDTO;
import com.kayafirat.blog.entity.Post;
import com.kayafirat.blog.entity.PostDetail;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PostService {

    List<PostViewDTO> getPosts();

    Page<Post> getPosts(int pageNumber, int pageSize, String sortedBy, String orderBy);

    Page<Post> getPostsByTitle(String keyword,int pageNumber, int pageSize, String sortedBy, String orderBy);

    Page<Post> getPostsByCategoryName(String categoryName,int pageNumber, int pageSize, String sortedBy, String orderBy);

    PostDetail getDetail(Long id);

    PostDetail addPost(PostDTO postDTO);

    PostDetail updatePost(PostDetail post);

    void deletePost(Long id);


}
