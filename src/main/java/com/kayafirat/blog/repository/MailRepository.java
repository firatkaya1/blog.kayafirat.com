package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.MailQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<MailQueue, Long> {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mail_queue WHERE queue_id = :id", nativeQuery = true)
    void deleteByQueueId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE mail_queue SET is_send=1,send_date=(SELECT CURRENT_TIMESTAMP) where queue_id = :id", nativeQuery = true)
    void updateStatus(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE mail_queue SET is_send=1,send_date=(SELECT CURRENT_TIMESTAMP) where queue_id IN (:queues)", nativeQuery = true)
    void updateStatusAll(@Param("queues") long[] queues);

    @Query(value = "SELECT * FROM mail_queue where is_send=0", nativeQuery = true)
    List<MailQueue> getMails();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mail_queue WHERE is_send = 1", nativeQuery = true)
    void deleteAllSend();

}
