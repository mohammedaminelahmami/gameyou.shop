package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.ProductDTO;
import com.youcode.gameyou.Entity.Category;
import com.youcode.gameyou.Entity.Image;
import com.youcode.gameyou.Entity.Product;
import com.youcode.gameyou.Entity.Store;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.CategoryRepository;
import com.youcode.gameyou.Repository.ImageRepository;
import com.youcode.gameyou.Repository.ProductRepository;
import com.youcode.gameyou.Repository.StoreRepository;
import com.youcode.gameyou.Service.Interfaces.IProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final ImageRepository imageRepository;
    private final Mapper<ProductDTO, Product> mapper;
    private final UploadFileService uploadFileService;

    @Override
    public void save(String name, Integer quantity, String title, String description, Double price, String categoryName, Long idStore, MultipartFile[] images) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setIsActive(true);

        // search category by name
        Category category = categoryRepository.findByName(categoryName).orElseThrow(()-> new RuntimeException("category not found"));
        product.setCategory(category);

        // search store by id
        Store store = storeRepository.findById(idStore).orElseThrow(()-> new RuntimeException("store not found"));
        product.setStore(store);

        productRepository.save(product); // save
        // save images
        for (MultipartFile image : images) {
            saveImage(image, product);
        }
    }

    @Override
    public String saveImage(MultipartFile imageProduct, Product product) {
        if(imageProduct == null) throw new RuntimeException("image not found");
        if(product == null) throw new RuntimeException("product not found");
        String path = uploadFileService.getOnePath(imageProduct);
        Image image = new Image();
        image.setPath(path);
        image.setProduct(product);
        image.setExt("");
        imageRepository.save(image);
        return path;
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
        Product findProductById = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
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

    @Override
    public List<ProductDTO> getAllProductsStore(int page, int size, Long idStore) {
        if(page > 0) page--;
        List<Product> products = productRepository.findAllByStoreId(PageRequest.of(page, size), idStore).stream().toList();
        // map productListEntity to productListDTO
        List<ProductDTO> productDTOS = mapper.convertListBToListA(products, ProductDTO.class);
        return productDTOS;
    }
}