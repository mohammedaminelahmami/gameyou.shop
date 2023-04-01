package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.ImageDTO;
import com.youcode.gameyou.Entity.Image;
import com.youcode.gameyou.Entity.Product;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.ImageRepository;
import com.youcode.gameyou.Repository.ProductRepository;
import com.youcode.gameyou.Service.Interfaces.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final Mapper<ImageDTO, Image> mapper;

    @Override
    public void delete(Long id) {
        // find image by id
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        imageRepository.delete(image);
    }

    @Override
    public List<ImageDTO> getAllProductImage(Long idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new RuntimeException("Product not found"));
        List<Image> imageList = imageRepository.findAllByProduct(product);
        List<ImageDTO> imageDTOS = mapper.convertListBToListA(imageList, ImageDTO.class);
        return imageDTOS;
    }
}
