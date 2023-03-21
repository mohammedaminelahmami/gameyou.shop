package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    void save(CategoryDTO addCategoryDTO);
    void update(CategoryDTO updateCategoryDTO, Long id);
    void delete(Long id);
    void deleteAll();
    CategoryDTO getOne(Long id);
    List<CategoryDTO> getAll(int page, int size);
}