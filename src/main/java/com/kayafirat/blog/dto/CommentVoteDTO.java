package com.kayafirat.blog.dto;

import java.util.Date;

public interface CommentVoteDTO {

    Long getId();
    Date getDate();
    String getUser_id();
    String getUser_name();
    String getProfile_photo();
}
