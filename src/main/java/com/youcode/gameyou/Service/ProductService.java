package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.ProductDTO;
import com.youcode.gameyou.Entity.Product;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.ProductRepository;
import com.youcode.gameyou.Service.Interfaces.IProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final Mapper<ProductDTO, Product> mapper;

    @Override
    public void save(ProductDTO addProductDTO) {
        // map productDTO to productEntity
        Product product = mapper.convertAtoB(addProductDTO, Product.class);
        product.setIsActive(true);
        productRepository.save(product); // save
    }

    @Override
    public void update(ProductDTO updaProductDTO, Long id) {
        Product findProductById = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        // map productEntity to productDTO
        ProductDTO productDTO = mapper.convertBtoA(findProductById, ProductDTO.class);

        if(updaProductDTO.getName() != null) productDTO.setName(updaProductDTO.getName());
        if(updaProductDTO.getQuantity() != null) productDTO.setQuantity(updaProductDTO.getQuantity());
        if(updaProductDTO.getTitle() != null) productDTO.setTitle(updaProductDTO.getTitle());
        if(updaProductDTO.getDescription() != null) productDTO.setDescription(updaProductDTO.getDescription());
        if(updaProductDTO.getPrice() != null) productDTO.setPrice(updaProductDTO.getPrice());
        if(updaProductDTO.getIsActive() != null) productDTO.setIsActive(updaProductDTO.getIsActive());

        // map productDTO to productEntity
        Product product = mapper.convertAtoB(productDTO, Product.class);
        // save the changes
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product findProductById = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        productRepository.delete(findProductById);
    }

    @Override
    public void deleteAll() {
        //
    }

    @Override
    public ProductDTO getOne(Long id) {
        Product findProductById = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        // map productEntity to productDTO
        ProductDTO productDTO = mapper.convertBtoA(findProductById, ProductDTO.class);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll(int page, int size) {
        if(page > 0) page--;
        List<Product> products = productRepository.findAll(PageRequest.of(page, size)).stream().toList();
        // map productListEntity to productListDTO
        List<ProductDTO> productDTOS = mapper.convertListBToListA(products, ProductDTO.class);
        return productDTOS;
    }
}