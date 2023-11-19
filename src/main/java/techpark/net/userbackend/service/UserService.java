package techpark.net.userbackend.service;

import techpark.net.keycloak.client.model.KeycloakUser;

import java.util.List;

public interface UserService {
    KeycloakUser getUserById(String id);
    List<KeycloakUser> getAllUsers();
}
