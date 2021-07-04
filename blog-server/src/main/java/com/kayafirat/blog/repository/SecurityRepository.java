package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security,Long> {

}
