package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.Request.Product.AddProductRequest;
import com.youcode.gameyou.Request.Product.UpdateProductRequest;
import com.youcode.gameyou.Response.Product.ProductResponse;

import java.util.List;

public interface IProductService {
    void save(AddProductRequest addProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(Long id);
    void deleteAll();
    List<ProductResponse> getAll(int page, int size);
    ProductResponse getOne(Long id);
}