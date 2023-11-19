package techpark.net.userbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techpark.net.keycloak.client.api.KeycloakApiClient;
import techpark.net.keycloak.client.model.KeycloakUser;
import techpark.net.userbackend.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final KeycloakApiClient keycloakApiClient;

    @Override
    public KeycloakUser getUserById(String id) {
        return keycloakApiClient.getUserById(id).getBody();
    }

    @Override
    public List<KeycloakUser> getAllUsers() {
        return keycloakApiClient.getAllUsers().getBody();
    }
}
