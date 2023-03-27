package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ClientDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IClientService {
    ClientDTO save (ClientDTO addNewClientDTO);
    ClientDTO updateInfo (ClientDTO updateClientInfoDTO, Long id);
    String uploadImage (MultipartFile imageFile);
    void delete (Long id);
    ClientDTO getOne (Long id);
    List<ClientDTO> getAll (int page, int size);
}