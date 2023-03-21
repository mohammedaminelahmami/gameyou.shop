package com.youcode.gameyou.Util;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Enum.Role;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class KeycloakUtils {

    @Value("${serverUrl}")
    String serverUrl;
    @Value("${realm}")
    String realm;
    @Value("${clientId}")
    String clientId;
    // final String clientSecret ;
    @Value("${username_kc}")
    String userName;
    @Value("${password_kc}")
    String password;

    public Keycloak getAdminKeycloakUser() {

        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType("password")
                .realm(realm)
                .clientId(clientId)
//              .clientSecret("38NhHZHruWl3Cp4jRYGz3qyCAEuQPFC8")
                .username(userName)
                .password(password)
                .build();
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm(realm);
    }

    public void setCredentials(String userId, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(password);
        UserResource userResource = getRealm().users().get(userId);
        userResource.resetPassword(credentialRepresentation);
    }

    private void addUserRole(String userId, String roleUser) {
        RoleRepresentation role = getRealm().roles().get(Role.ROLE_ADMIN.toString()).toRepresentation();
        UserResource userResource = getRealm().users().get(userId);
        userResource.roles().realmLevel().add(List.of(role));
    }

    public void deleteUser(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.remove();
    }

    public void sendMailVerification(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.sendVerifyEmail();
    }

    public void sendResetPasword(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public String createKeycloakUserWithRole(UserDTO userDTO) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setUsername(userDTO.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userDTO.getEmail());

        try {
            Response response = getRealm().users().create(userRepresentation);
            String userId = CreatedResponseUtil.getCreatedId(response);
            System.out.println("idUser: " + userId);
            this.setCredentials(userId, userDTO.getHashedPassword());
            System.out.println("from create kc : " + userDTO.getRole());
            addUserRole(userId, userDTO.getRole().toString());
            return userId;
        } catch (Exception e) {
            log.error("Error while creating user in keycloak: " + e.getMessage());
            return null;
        }
    }

    public String updateKeycloakUser(String userId, UserDTO userDTO) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setUsername(userDTO.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userDTO.getEmail());
        this.setCredentials(userId, userDTO.getHashedPassword());

        try {
            UserResource userResource = getRealm().users().get(userId);
            userResource.update(userRepresentation);
            return userId;
        } catch (Exception e) {
            log.error("Error while updating user in keycloak: " + e.getMessage());
            return null;
        }
    }
}