package com.youcode.gameyou.Response.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}