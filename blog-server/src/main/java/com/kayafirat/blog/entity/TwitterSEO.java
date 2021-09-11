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
@Table(name = "twitterSEO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class TwitterSEO extends SEO {

    @Column
    private String creator;

    @Column
    private String card;

    public TwitterSEO(PostDTO postDTO){
        this.setId(postDTO.getTwitterId());
        this.setTitle(postDTO.getTwitterTitle());
        this.setDescription(postDTO.getTwitterDescription());
        this.setImage(postDTO.getTwitterImagepath());
        this.creator = postDTO.getTwitterCreator();
        this.card = postDTO.getTwitterCard();
    }
}
