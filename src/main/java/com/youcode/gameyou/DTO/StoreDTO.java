package com.youcode.gameyou.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StoreDTO {
    private Long id;
    private String name;
    private String description;
    private String imagepath;
    private int stars;
    private Boolean isactive;
}