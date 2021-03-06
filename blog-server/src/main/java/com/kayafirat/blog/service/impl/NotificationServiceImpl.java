package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.NotificationDTO;
import com.kayafirat.blog.dto.NotificationSaveDTO;
import com.kayafirat.blog.entity.Notification;
import com.kayafirat.blog.entity.NotificationPermission;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.exception.custom.UserNotFoundException;
import com.kayafirat.blog.repository.NotificationRepository;
import com.kayafirat.blog.repository.UserRepository;
import com.kayafirat.blog.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;


    @Override
    public void addNotification(NotificationSaveDTO notificationSaveDTO) {
        if(notificationSaveDTO.getSendAll()){
            List<User> users = userRepository.findAll();
            List<Notification> notifications = new ArrayList<>();
            for (User user:users) {
                Notification notification = new Notification();
                notification.setCreatedDate(new Date());
                notification.setLink(notificationSaveDTO.getNotificationLink());
                notification.setMessage(notificationSaveDTO.getNotificationBody());
                notification.setTitle(notificationSaveDTO.getNotificationTitle());
                notification.setRead(false);
                notification.setIcon(notificationSaveDTO.getNotificationIcon());
                notifications.add(notification);
                notification.setUser(user);
            }
            notificationRepository.saveAll(notifications);
        } else {
            List<User> users = userRepository.findUsers(notificationSaveDTO.getUserIds());
            List<Notification> notifications = new ArrayList<>();
            for (User user:users) {
                Notification notification = new Notification();
                notification.setCreatedDate(new Date());
                notification.setLink(notificationSaveDTO.getNotificationLink());
                notification.setMessage(notificationSaveDTO.getNotificationBody());
                notification.setTitle(notificationSaveDTO.getNotificationTitle());
                notification.setRead(false);
                notification.setIcon(notificationSaveDTO.getNotificationIcon());
                notifications.add(notification);
                notification.setUser(user);
            }
            notificationRepository.saveAll(notifications);
        }
    }

    @Override
    public Page<NotificationDTO> getAllNotification(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return notificationRepository.findAllPageable(pageable);
    }


    @Override
    public Page<NotificationDTO> getNotifications(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return userRepository.findAllPageable(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()),pageable);
    }

    @Override
    public Integer hasNotification(Long id) {
        return userRepository.hasNotification(id);
    }

    @Override
    public void read(Long id) {
        userRepository.updateReadByUserID(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()),id);
    }

    @Override
    public void allRead() {
        userRepository.updateAllReadByUserID(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @Override
    public void addNotification(Notification notification) {
        User user = userRepository.findById(notification.getUser().getId()).orElseThrow(UserNotFoundException::new);
        notification.setUser(user);
        Set<Notification> notifications = user.getNotifications();
        notifications.add(notification);
        user.setNotifications(notifications);
        userRepository.save(user);
    }

    @Override
    @CachePut(value = "notificationPermissions", key = "#id")
    public NotificationPermission updateNotificationPermissions(NotificationPermission notificationPermission,Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        notificationPermission.setId(user.getNotificationPermission().getId());
        user.setNotificationPermission(notificationPermission);
        return userRepository.save(user).getNotificationPermission();
    }

    @Override
    public void deleteNotification(Long id) {
        userRepository.deleteNotificationById(id,Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @Override
    public void deleteAllNotification() {
        userRepository.deleteAllNotifications(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}