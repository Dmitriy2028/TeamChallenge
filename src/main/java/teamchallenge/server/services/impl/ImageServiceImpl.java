package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.dto.ResponseImageDto;
import teamchallenge.server.entities.Image;
import teamchallenge.server.exception.FileUploadException;
import teamchallenge.server.repositories.ImageRepository;
import teamchallenge.server.services.ImageService;
import teamchallenge.server.utils.ImageUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile image) {
        try {
            Image imageEntity = new Image();
            imageEntity.setName(image.getOriginalFilename());
            imageEntity.setContentType(image.getContentType());
            imageEntity.setBytes(ImageUtils.compressImage(image.getBytes()));
            return imageRepository.save(imageEntity);
        } catch (IOException e) {
            log.error("Error while saving image", e);
            throw new FileUploadException("Error while saving image");
        }
    }

    @Override
    public ResponseImageDto getImageDto(Image image) {
        if (image == null) {
            return null;
        }
        return ResponseImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .contentType(image.getContentType())
                .bytes(ImageUtils.decompressImage(image.getBytes()))
                .build();
    }
}
