package com.kayafirat.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String header;
    private Long[] categories;
    private String body;
    private boolean isPublish;

    private Long googleId;
    private String googleTitle;
    private String googleDescription;
    private String googlePath;
    private String googleTag;
    private String keywords;

    private Long twitterId;
    private String twitterDescription;
    private String twitterImagepath;
    private String twitterTitle;
    private String twitterTag;
    private String twitterCard;
    private String twitterCreator;

    private Long facebookId;
    private String facebookDescription;
    private String facebookImagepath;
    private String facebookTitle;
    private String facebookTag;
    private String facebookAuthor;
    private String facebookSitename;
    private String facebookUrl;
    private String facebookType;

    private Long metaId;

}
