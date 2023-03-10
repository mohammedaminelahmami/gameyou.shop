package com.youcode.gameyou.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class ImageDTO {
    private Long id;
    private String path;
    private String ext;
}