package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.ProductDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Request.Product.AddProductRequest;
import com.youcode.gameyou.Request.Product.UpdateProductRequest;
import com.youcode.gameyou.Response.Product.ProductResponse;
import com.youcode.gameyou.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Mapper<ProductDTO, ProductResponse> mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save (@RequestBody @Valid AddProductRequest addProductRequest) {
        // map AddProductRequest to productDTO
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(addProductRequest, productDTO);
        productService.save(productDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void update (@RequestBody @Valid UpdateProductRequest updateProductRequest, @PathVariable Long id) {
        // map updateProductRequest to ProductDTO
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(updateProductRequest, productDTO);
        productService.update(productDTO, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        productService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponse getOne (@PathVariable Long id) {
        ProductDTO productDTO = productService.getOne(id);
        ProductResponse productResponse = mapper.convertAtoB(productDTO, ProductResponse.class);
        return productResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponse> getAll (@RequestParam int page, @RequestParam int size) {
        List<ProductDTO> productDTOS = productService.getAll(page, size);
        // map productDTOS to productResponses
        List<ProductResponse> productResponses = mapper.convertListAToListB(productDTOS, ProductResponse.class);
        return productResponses;
    }
}