package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.ImageDTO;
import com.kayafirat.blog.entity.Image;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageService {

    private final ImageRepository imageRepository;
    private final Environment env;


    public List<ImageDTO> getList(){
        return imageRepository.findAllImages();
    }

    public Image saveImage(MultipartFile multipartFile) throws IOException {
        byte[] bytes;
        Image image = new Image(multipartFile);
        String uuid = UUID.randomUUID().toString();
        try {
            String pathURI = env.getProperty("post.default.photo-path") + uuid +  "." + multipartFile.getOriginalFilename().split("\\.")[1];
            bytes = multipartFile.getBytes();
            Path path = Paths.get(pathURI);
            Files.write(path, bytes);
            image.setFullPath("https://image.kayafirat.com/images/"+ uuid +  "." + multipartFile.getOriginalFilename().split("\\.")[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageRepository.save(image);
    }

    public String getImage(Long id){
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found."));
        return image.getFullPath();
    }
}
