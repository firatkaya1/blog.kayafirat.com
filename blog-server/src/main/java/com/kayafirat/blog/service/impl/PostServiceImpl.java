package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.PostDTO;
import com.kayafirat.blog.dto.PostViewDTO;
import com.kayafirat.blog.entity.*;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.exception.custom.PostIDNotFoundException;
import com.kayafirat.blog.repository.CategoryRepository;
import com.kayafirat.blog.repository.CommentRepository;
import com.kayafirat.blog.repository.PostDetailRepository;
import com.kayafirat.blog.repository.PostRepository;
import com.kayafirat.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;

    public List<PostViewDTO> getPosts(){
        return postRepository.findAllPosts();
    }

    @Override
    @Cacheable(cacheNames ="pageable", key = "#pageNumber",unless = "#result?.getNumberOfElements() == 0 " )
    public Page<Post> getPosts(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Post> result = postRepository.findAll(pageable);
        return result;
    }

    @Override
    @Cacheable(cacheNames ="pageableTitle", key = "#keyword+#pageNumber",unless = "#result?.getNumberOfElements() == 0 " )
    public Page<Post> getPostsByTitle(String keyword, int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Post> result = postRepository.findByTitleContainingIgnoreCase(keyword,pageable);
        return result;
    }

    @Override
    @Cacheable(cacheNames ="pageableCategory", key = "#categoryName+#pageNumber",unless = "#result?.getNumberOfElements() == 0 " )
    public Page<Post> getPostsByCategoryName(String categoryName,int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Post> result = postRepository.findPostByCategoryName(categoryName,pageable);
        return result;
    }

    @Override
    @Cacheable(cacheNames = "detailID", key = "#id")
    public PostDetail getDetail(Long id) {
        return postDetailRepository.findPostDetailByPostId(id)
                .orElseThrow(() -> new PostIDNotFoundException(id));
    }

    @Override
    @Cacheable(cacheNames = "detailID", key = "#postDetail.id")
    @CacheEvict(cacheNames = {"pageable","pageableCategory","pageableCategory"}, allEntries = true)
    public PostDetail addPost(PostDTO postDTO) {
        Post post = new Post();

        post.setCreatedDate(new Date());
        post.setTitle(postDTO.getTitle());
        post.setHeader(postDTO.getHeader());
        post.setHide(postDTO.isPublish());

        if(postDTO.getCategories() != null && postDTO.getCategories().length > 0){
            Set<Category> categorySet = new HashSet<>();
            for (Long categoryId:postDTO.getCategories()) {
                Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category bulunamadı"));
                categorySet.add(category);
            }
            post.setCategory(categorySet);
        }


        PostDetail postDetail = new PostDetail();
        postDetail.setBody(postDTO.getBody());
        postDetail.setPost(post);

        GoogleSEO googleSEO = new GoogleSEO(postDTO);
        FacebookSEO facebookSEO = new FacebookSEO(postDTO);
        TwitterSEO twitterSEO = new TwitterSEO(postDTO);

        Meta meta = new Meta(googleSEO,twitterSEO,facebookSEO);
        postDetail.setMeta(meta);

        postDetail = postDetailRepository.save(postDetail);
        return postDetail;
    }

    @Override
    @CachePut(cacheNames = "detailID", key = "#postDetail.post.id")
    @CacheEvict(cacheNames = {"pageable","pageableCategory","pageableCategory"}, allEntries = true)
    public PostDetail updatePost(PostDetail postDetail) {
        if (!postDetailRepository.existsById(postDetail.getId()))
            throw new PostIDNotFoundException(postDetail.getId());
        return postDetailRepository.save(postDetail);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "detailID",key = "#id"),
            @CacheEvict(cacheNames = {"pageable","pageableCategory","pageableCategory"}, allEntries = true)})
    public void deletePost(Long id) {
        PostDetail postDetail = getDetail(id);
        postRepository.deleteById(id);
        postDetailRepository.deleteById(postDetail.getId());
        commentRepository.deleteByPostId(postDetail.getId());
    }

}


