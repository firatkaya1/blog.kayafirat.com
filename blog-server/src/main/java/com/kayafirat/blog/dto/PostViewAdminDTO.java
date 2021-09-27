package com.kayafirat.blog.dto;

import com.kayafirat.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostViewAdminDTO {

    private Long postId;
    private String postTitle;
    private List<String> categories;
    private Date createdDate;
    private Date updatedDate;
    private Long totalComment;
    private Long totalView;
    private Boolean isHide;


    public PostViewAdminDTO(Post post){
        this.postId = post.getId();
        this.postTitle = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.categories = post.getCategory().stream().map(c -> c.getName()).collect(Collectors.toList());
        this.updatedDate = post.getUpdatedDate();
        this.totalView = post.getView();
        this.isHide = post.isHide();
    }
}
