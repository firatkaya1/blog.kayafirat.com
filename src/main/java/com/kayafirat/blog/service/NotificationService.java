package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.NotificationDTO;
import com.kayafirat.blog.entity.MailPermission;
import com.kayafirat.blog.entity.Notification;
import com.kayafirat.blog.entity.NotificationPermission;
import org.springframework.data.domain.Page;

public interface NotificationService {

    Page<NotificationDTO> getNotifications(int pageNumber, int pageSize, String sortedBy, String orderBy);

    Integer hasNotification(Long id);

    void read(Long id);

    void allRead();

    void addNotification(Notification notification);

    NotificationPermission updateNotificationPermissions(NotificationPermission notificationPermission,Long id);

    void deleteNotification(Long id);

    void deleteAllNotification();

}
