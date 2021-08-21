package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.ImageDTO;
import com.kayafirat.blog.entity.File;
import com.kayafirat.blog.entity.Image;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.repository.FileRepository;
import com.kayafirat.blog.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileRepository fileRepository;


    public List<ImageDTO> getList(){
        return imageRepository.findAllImages();
    }

    public Image saveImage(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setImageName(multipartFile.getName());
        File file = new File(multipartFile);
        image.setImageFile(file);
        return imageRepository.save(image);
    }

    public byte[] getImage(Long id){
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found."));
        return Base64.encodeBase64(image.getImageFile().getFile());
    }
}
