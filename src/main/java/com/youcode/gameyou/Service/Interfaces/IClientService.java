package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.Response.Client.ClientResponse;

import java.util.List;

public interface IClientService {
    ClientDTO save (ClientDTO addNewClientDTO);
    ClientDTO updateInfo (ClientDTO updateClientInfoDTO, Long id);
    void delete (Long id);
    ClientDTO getOne (Long id);
    List<ClientDTO> getAll (int page, int size);
}