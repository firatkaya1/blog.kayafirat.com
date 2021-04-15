package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.Post;
import com.kayafirat.blog.entity.PostDetail;
import org.springframework.data.domain.Page;


public interface PostService {

    Page<Post> getPosts(int pageNumber, int pageSize, String sortedBy, String orderBy);

    Page<Post> getPostsByTitle(String keyword,int pageNumber, int pageSize, String sortedBy, String orderBy);

    Page<Post> getPostsByCategoryName(String categoryName,int pageNumber, int pageSize, String sortedBy, String orderBy);

    PostDetail getDetail(Long id);

    PostDetail addPost(PostDetail postDetail);

    PostDetail updatePost(PostDetail post);

    void deletePost(Long id);


}
