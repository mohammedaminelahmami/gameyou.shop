package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.ImageDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Response.Image.ImageResponse;
import com.youcode.gameyou.Service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final Mapper<ImageDTO, ImageResponse> mapper;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idProduct}")
    public List<ImageResponse> getAllProductImages(@PathVariable Long idProduct) {
        List<ImageDTO> imageDTOS = imageService.getAllProductImage(idProduct);
        List<ImageResponse> imageResponses = mapper.convertListAToListB(imageDTOS, ImageResponse.class);
        return imageResponses;
    }
}
