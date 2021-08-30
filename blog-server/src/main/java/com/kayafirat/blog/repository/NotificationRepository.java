package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.NotificationDTO;
import com.kayafirat.blog.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "SELECT n1.*,u1.username FROM notification as n1 LEFT JOIN user as u1 on u1.id = n1.user_id",nativeQuery = true)
    Page<NotificationDTO> findAllPageable(Pageable pageable);
}
