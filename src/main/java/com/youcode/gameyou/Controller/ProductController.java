package com.youcode.gameyou.Controller;

import com.youcode.gameyou.Request.Product.AddProductRequest;
import com.youcode.gameyou.Request.Product.UpdateProductRequest;
import com.youcode.gameyou.Response.Product.ProductResponse;
import com.youcode.gameyou.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save (@RequestBody @Valid AddProductRequest addProductRequest) {
        productService.save(addProductRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    public void update (@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        productService.update(updateProductRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{/id}")
    public void delete (@PathVariable Long id) {
        productService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{/id}")
    public ProductResponse getOne(@PathVariable Long id) {
        return productService.getOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponse> getAll (@RequestParam int page, @RequestParam int size) {
        return productService.getAll(page, size);
    }
}
