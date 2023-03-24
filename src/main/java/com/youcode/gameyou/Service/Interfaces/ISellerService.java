package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.SellerDTO;

import java.util.List;

public interface ISellerService {
    SellerDTO save (SellerDTO addNewSellerDTO);
    SellerDTO updateInfo (SellerDTO updateSellerInfoDTO, Long id);
    void delete (Long id);
    SellerDTO getOne (Long id);
    List<SellerDTO> getAll (int page, int size);
}