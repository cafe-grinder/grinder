package com.grinder.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {

    @Getter
    @Setter
    public static class UpdateRequest {
        private String cafeId;
        private MultipartFile image;
    }
}
