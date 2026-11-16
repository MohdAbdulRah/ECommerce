package org.fitness.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final Path uploadPath = Paths.get("uploads","products").toAbsolutePath().normalize();

    private static final Map<String,String> IMAGES_TYPES = Map.of(
            "image/jpeg" ,".jpeg",
            "image/png" ,".png",
            "image/webp" ,".webp"
    );
    public String saveImage(MultipartFile image) {
        if(image == null || image.isEmpty()){
            throw new IllegalArgumentException("Invalid Image File");
        }

        String extension = IMAGES_TYPES.get(image.getContentType());
        if(extension == null){
            throw new IllegalArgumentException("Only PNG JPEG and WEBP Files are allowed");
        }

        try {
            Files.createDirectories(uploadPath);
            String fileName = UUID.randomUUID() + extension;
            Path targetPath = uploadPath.resolve(fileName).normalize();

            Files.copy(image.getInputStream(),targetPath);
            return "/uploads/products/"+fileName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
