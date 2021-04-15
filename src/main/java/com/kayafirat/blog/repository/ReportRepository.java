package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Modifying
    @Query(value = "UPDATE report SET is_read=1 where report_id = :id", nativeQuery = true)
    void updateReadStatus(@Param("id") Long id);
}
