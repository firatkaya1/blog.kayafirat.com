package com.kayafirat.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSaveDTO {

    private String notificationIcon;
    private String notificationTitle;
    private String notificationLink;
    private String notificationBody;
    private Boolean sendAll;
    private Long userIds[];

}
