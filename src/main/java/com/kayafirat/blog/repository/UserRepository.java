package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.NotificationDTO;
import com.kayafirat.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM notification where user_id = :userId ",
            countQuery = "select count(id) from notification where user_id = :userId ",nativeQuery = true)
    Page<NotificationDTO> findAllPageable(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "SELECT count(id) FROM blog.notification where user_id = :userId and is_read = 0 or is_read = null ",nativeQuery = true)
    Integer hasNotification(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE blog.notification set is_read = true, modified_date=(SELECT NOW()) where user_id=:userId",nativeQuery = true)
    void updateAllReadByUserID(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE blog.notification SET is_read = true, modified_date=(SELECT NOW()) WHERE user_id=:userId AND id=:id",nativeQuery = true)
    void updateReadByUserID(@Param("userId") Long userId,@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM blog.notification WHERE id=:notificationId AND user_id = :userId",nativeQuery = true)
    void deleteNotificationById(@Param("notificationId") Long notificationId,@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM blog.notification WHERE user_id = :userId",nativeQuery = true)
    void deleteAllNotifications(@Param("userId") Long userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

}
