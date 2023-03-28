package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    void save(ProductDTO addProductDTO);
    String saveImage(MultipartFile image, Long productId);
    void update(ProductDTO updateProductDTO, Long id);
    void delete(Long id);
    void deleteAll();
    List<ProductDTO> getAll(int page, int size);
    ProductDTO getOne(Long id);
}