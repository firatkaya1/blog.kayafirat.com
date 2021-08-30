package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.NotificationDTO;
import com.kayafirat.blog.dto.NotificationSaveDTO;
import com.kayafirat.blog.entity.Notification;
import com.kayafirat.blog.entity.NotificationPermission;
import org.springframework.data.domain.Page;

public interface NotificationService {

    Page<NotificationDTO> getAllNotification(int pageNumber, int pageSize, String sortedBy, String orderBy);

    Page<NotificationDTO> getNotifications(int pageNumber, int pageSize, String sortedBy, String orderBy);

    Integer hasNotification(Long id);

    void read(Long id);

    void allRead();

    void addNotification(Notification notification);

    void addNotification(NotificationSaveDTO notification);

    NotificationPermission updateNotificationPermissions(NotificationPermission notificationPermission,Long id);

    void deleteNotification(Long id);

    void deleteAllNotification();

}
