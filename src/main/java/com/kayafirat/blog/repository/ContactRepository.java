package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Query(value = "UPDATE contact SET is_read=1 where report_id = :id", nativeQuery = true)
    void updateReadStatus(@Param("id") Long id);
}
