package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ClientDTO;

import java.util.List;

public interface IClientService {
    ClientDTO save (ClientDTO addNewClientDTO);
    ClientDTO updateInfo (ClientDTO updateClientInfoDTO, Long id);
    void delete (Long id);
    ClientDTO getOne (Long id);
    List<ClientDTO> getAll (int page, int size);
}