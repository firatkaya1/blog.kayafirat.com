package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.LogResponse;
import com.kayafirat.blog.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {

    @Query(value = "SELECT sec_to_time(floor(avg(log_time)/1000)) as log_time,class_name,method_name FROM log group by class_name,method_name",nativeQuery = true)
    List<LogResponse> findAllResponse();
}
