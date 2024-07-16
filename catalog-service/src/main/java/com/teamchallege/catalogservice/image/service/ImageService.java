package com.teamchallege.catalogservice.image.service;

import com.teamchallege.catalogservice.image.dto.ResponseImageDto;
import com.teamchallege.common.entities.catalog.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    Image saveImage(MultipartFile image);

    ResponseImageDto getImageDto(Image images);
}
