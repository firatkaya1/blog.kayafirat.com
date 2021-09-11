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
@Table(name = "GoogleSEO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class GoogleSEO extends SEO {

    @Column
    private String keywords;

    public GoogleSEO(PostDTO postDTO){
        this.setId(postDTO.getGoogleId());
        this.setImage(postDTO.getGooglePath());
        this.setKeywords(postDTO.getKeywords());
        this.setDescription(postDTO.getGoogleDescription());
        this.setTitle(postDTO.getTitle());
    }

}
