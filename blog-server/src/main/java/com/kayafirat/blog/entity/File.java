package com.kayafirat.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long fileId;
    private String fileName;
    private Long fileSize;
    private String fileContenttype;

    @Lob
    private byte[] file;

    public File(MultipartFile multipartFile) throws IOException {
        this.file = multipartFile.getBytes();
        this.fileName = multipartFile.getOriginalFilename();
        this.fileContenttype = multipartFile.getContentType();
        this.fileSize = multipartFile.getSize();
    }
}
