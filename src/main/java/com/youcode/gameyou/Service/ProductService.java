package com.youcode.gameyou.Service;

import com.youcode.gameyou.Request.Product.AddProductRequest;
import com.youcode.gameyou.Request.Product.UpdateProductRequest;
import com.youcode.gameyou.Response.Product.ProductResponse;
import com.youcode.gameyou.Service.Interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Override
    public void save(AddProductRequest addProductRequest) {

    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ProductResponse getOne(Long id) {
        return null;
    }

    @Override
    public List<ProductResponse> getAll(int page, int size) {
        return null;
    }

}