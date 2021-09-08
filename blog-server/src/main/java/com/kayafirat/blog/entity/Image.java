package com.kayafirat.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long imageId;
    private String imageOriginalName;
    private Long fileSize;
    private String fileContenttype;
    private String fullPath;

    public Image(MultipartFile multipartFile){
        this.imageOriginalName = multipartFile.getOriginalFilename();
        this.fileSize = multipartFile.getSize();
        this.fileContenttype = multipartFile.getContentType();
    }
}
