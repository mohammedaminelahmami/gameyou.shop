package com.youcode.gameyou.DTO;

import lombok.*;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private Boolean isActive;
}