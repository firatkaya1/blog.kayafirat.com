package com.kayafirat.blog.repository;

import com.kayafirat.blog.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<Config,Long> {

    Optional<Config> findByConfigKod(String configKod);
}
