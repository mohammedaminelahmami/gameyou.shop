package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.Request.Client.AddNewClientRequest;
import com.youcode.gameyou.Request.Client.UpdateClientInfoRequest;
import com.youcode.gameyou.Request.Client.UpdateClientPassword;
import com.youcode.gameyou.Response.Client.ClientResponse;

import java.util.List;

public interface IClientService {
    ClientResponse save(AddNewClientRequest addNewClientRequest);
    ClientResponse getOne(String id);
    List<ClientResponse> getAll(int page, int size);
    ClientResponse updateInfo(UpdateClientInfoRequest updateClientInfoRequest);
    void updatePassword(UpdateClientPassword updateClientPassword);
    void delete(String id);
}