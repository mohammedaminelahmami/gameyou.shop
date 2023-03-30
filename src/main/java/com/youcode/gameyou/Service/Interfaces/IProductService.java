package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ProductDTO;
import com.youcode.gameyou.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    void save(String name, Integer quantity, String title, String description, Double price, String categoryName,Long id, MultipartFile[] images);
    String saveImage(MultipartFile imageProduct, Product product);
    void update(ProductDTO updateProductDTO, Long id);
    void delete(Long id);
    void deleteAll();
    List<ProductDTO> getAll(int page, int size);
    List<ProductDTO> getAllProductsStore(int page, int size, Long idStore);
    ProductDTO getOne(Long id);
}