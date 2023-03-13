package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.Mapper.IMapperDTO;
import com.youcode.gameyou.DTO.ProductDTO;
import com.youcode.gameyou.Entity.Product;
import com.youcode.gameyou.Repository.ProductRepository;
import com.youcode.gameyou.Request.Product.AddProductRequest;
import com.youcode.gameyou.Request.Product.UpdateProductRequest;
import com.youcode.gameyou.Response.Product.ProductResponse;
import com.youcode.gameyou.Service.Interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final IMapperDTO<ProductResponse, Product> mapper;
    @Override
    public void save(AddProductRequest addProductRequest) {
        // map productRequest to productDTO
        ProductDTO productDTO = ProductDTO.builder()
                                .name(addProductRequest.getName())
                                .quantity(addProductRequest.getQuantity())
                                .title(addProductRequest.getTitle())
                                .description(addProductRequest.getDescription())
                                .price(addProductRequest.getPrice())
                                .build();
        // map productDTO to entity
        Product product = Product.builder()
                            .name(productDTO.getName())
                            .quantity(productDTO.getQuantity())
                            .title(productDTO.getTitle())
                            .description(productDTO.getDescription())
                            .price(productDTO.getPrice())
                            .build();
        productRepository.save(product);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        // find the product
        Product product = productRepository.findById(Long.parseLong(updateProductRequest.getId())).orElseThrow(() -> new RuntimeException("Product not found !"));
        if(!updateProductRequest.getName().isEmpty()) product.setName(updateProductRequest.getName());
        if(!updateProductRequest.getQuantity().isEmpty()) product.setQuantity(updateProductRequest.getQuantity());
        if(!updateProductRequest.getTitle().isEmpty()) product.setTitle(updateProductRequest.getTitle());
        if(!updateProductRequest.getDescription().isEmpty()) product.setDescription(updateProductRequest.getDescription());
        if(!String.valueOf(updateProductRequest.getPrice()).isEmpty()) product.setPrice(updateProductRequest.getPrice());
        // save the update
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        // find the product
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found !"));
        // delete the product
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        //
    }

    @Override
    public ProductResponse getOne(Long id) {
        // get the entity
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        // map product to productDTO
        ProductDTO productDTO = ProductDTO.builder()
                                .name(product.getName())
                                .quantity(product.getQuantity())
                                .title(product.getTitle())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .build();
        // map productDTO to productResponse
        ProductResponse productResponse = ProductResponse.builder()
                                            .name(productDTO.getName())
                                            .quantity(productDTO.getQuantity())
                                            .title(productDTO.getTitle())
                                            .description(productDTO.getDescription())
                                            .price(productDTO.getPrice())
                                            .build();
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAll(int page, int size) {
        List<Product> products = productRepository.findAll(PageRequest.of(page, size)).stream().toList();
        List<ProductResponse> productResponses = mapper.convertListToListDto(products, ProductResponse.class);
        return productResponses;
    }
}