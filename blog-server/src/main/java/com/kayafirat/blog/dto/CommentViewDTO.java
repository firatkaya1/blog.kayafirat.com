package com.kayafirat.blog.dto;

import java.util.Date;

public interface CommentViewDTO {

    Long getCommentId();
    String getCommentBody();
    Boolean getCommentIsdelete();
    Boolean getCommentIshide();
    String getPostTitle();
    String getusername();
    Long gettotalVote();
    Date getCommentCreatedDate();
}
