package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.CategoryDTO;
import com.youcode.gameyou.Entity.Category;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.CategoryRepository;
import com.youcode.gameyou.Service.Interfaces.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final Mapper<CategoryDTO, Category> mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void save(CategoryDTO addCategoryDTO) {
        // map categoryDTO to categoryEntity
        Category category = mapper.convertAtoB(addCategoryDTO, Category.class);
        category.setIsActive(true);
        categoryRepository.save(category); // save
    }

    @Override
    public void update(CategoryDTO updateCategoryDTO, Long id) {
        Category findCategoryById = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
        // map categoryEntity to categoryDTO
        CategoryDTO categoryDTO = mapper.convertBtoA(findCategoryById, CategoryDTO.class);

        if(updateCategoryDTO.getName() != null) categoryDTO.setName(updateCategoryDTO.getName());
        if(updateCategoryDTO.getDescription() != null) categoryDTO.setDescription(updateCategoryDTO.getDescription());
        if(updateCategoryDTO.getImagePath() != null) categoryDTO.setImagePath(updateCategoryDTO.getImagePath());
        if(updateCategoryDTO.getIsActive() != null) categoryDTO.setIsActive(updateCategoryDTO.getIsActive());

        // map categoryDTO to categoryEntity
        Category category = mapper.convertAtoB(categoryDTO, Category.class);
        // save the changes
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category findCategoryById = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
        categoryRepository.delete(findCategoryById);
    }

    @Override
    public void deleteAll() {
        //
    }

    @Override
    public CategoryDTO getOne(Long id) {
        Category findCategoryById = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
        // map categoryEntity to categoryDTO
        CategoryDTO categoryDTO = mapper.convertBtoA(findCategoryById, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAll(int page, int size) {
        if(page > 0) page--;
        List<Category> categories = categoryRepository.findAll(PageRequest.of(page, size)).stream().toList();
        // map categoryListEntity to categoryListDTO
        List<CategoryDTO> categoryDTOS = mapper.convertListBToListA(categories, CategoryDTO.class);
        return categoryDTOS;
    }
}