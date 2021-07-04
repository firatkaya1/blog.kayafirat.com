package com.kayafirat.blog.dto;

import java.util.Date;

public interface CommentDTO {

      Long getId();
      String getBody();
      Date getCreated_date();
      Date getUpdate_date();
      String getUser_name();
      String getProfile_photo();
      Long getPost_id();
      Long getTotal_vote();

}
