package com.youcode.gameyou.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {
    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private int stars;
    private Boolean isActive;
}