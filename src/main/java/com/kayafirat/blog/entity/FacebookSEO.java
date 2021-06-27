package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kayafirat.blog.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facebookSEO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class FacebookSEO extends SEO {

    @Column
    private String type;

    @Column
    private String siteName;

    @Column
    private String url;

    @Column
    private String author;

    public FacebookSEO(PostDTO postDTO){
        this.setTitle(postDTO.getTitle());
        this.setDescription(postDTO.getFacebookDescription());
        this.setImage(postDTO.getFacebookImagepath());
        this.type = postDTO.getFacebookType();
        this.siteName = postDTO.getFacebookSitename();
        this.url = postDTO.getFacebookUrl();
        this.author = postDTO.getFacebookAuthor();
    }
}
