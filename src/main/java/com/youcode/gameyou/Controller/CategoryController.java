package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.CategoryDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Request.Category.AddCategoryRequest;
import com.youcode.gameyou.Request.Category.UpdateCategoryRequest;
import com.youcode.gameyou.Response.Category.CategoryResponse;
import com.youcode.gameyou.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final Mapper<CategoryDTO, CategoryResponse> mapper;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save (@RequestBody @Valid AddCategoryRequest addCategoryRequest, @RequestParam MultipartFile image) {
        // map AddCategoryRequest to categoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(addCategoryRequest, categoryDTO);
        categoryService.save(categoryDTO, image);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void update (@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest, @PathVariable Long id) {
        // map updateCategoryRequest to CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(updateCategoryRequest, categoryDTO);
        categoryService.update(categoryDTO, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        categoryService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CategoryResponse getOne (@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.getOne(id);
        // map categoryDTO to response
        CategoryResponse categoryResponse = mapper.convertAtoB(categoryDTO, CategoryResponse.class);
        return categoryResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoryResponse> getAll (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        List<CategoryDTO> categoryDTOS = categoryService.getAll(page, size);
        // map categoryDTOS to categoryResponse
        List<CategoryResponse> categoryResponses = mapper.convertListAToListB(categoryDTOS, CategoryResponse.class);
        return categoryResponses;
    }
}