package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ImageDTO;

import java.util.List;

public interface IImageService {
    void delete(Long id);
    List<ImageDTO> getAllProductImage(Long idProduct);
}
