package com.kayafirat.blog.dto;

import java.util.Date;

public interface NotificationDTO {
    
     Long getId();
     String getTitle();
     String getMessage();
     String getLink();
     Date getCreated_Date();
     Date getModified_Date();
     Boolean getIs_Read();
}
