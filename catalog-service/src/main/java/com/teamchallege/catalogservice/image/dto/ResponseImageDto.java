package com.teamchallege.catalogservice.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseImageDto {
    private long id;
    private String name;
    private String contentType;
    private byte[] bytes;
}
