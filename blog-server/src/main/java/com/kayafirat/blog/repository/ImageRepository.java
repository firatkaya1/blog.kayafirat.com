package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.ImageDTO;
import com.kayafirat.blog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository  extends JpaRepository<Image,Long> {

    @Query(value = "SELECT image_id as imageId,image_name as imageName from image",nativeQuery = true)
    List<ImageDTO> findAllImages();

}
