package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ProductDTO;

import java.util.List;

public interface IProductService {
    void save(ProductDTO addProductDTO);
    void update(ProductDTO updateProductDTO, Long id);
    void delete(Long id);
    void deleteAll();
    List<ProductDTO> getAll(int page, int size);
    ProductDTO getOne(Long id);
}