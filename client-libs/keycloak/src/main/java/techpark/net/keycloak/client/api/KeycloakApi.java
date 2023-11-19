package techpark.net.keycloak.client.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techpark.net.keycloak.client.model.KeycloakUser;

import java.util.List;

@Validated
@Tag(name = "Keycloak", description = "Методы для работы с keycloak API")
public interface KeycloakApi {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = "*/*"
    )
    ResponseEntity<List<KeycloakUser>> getAllUsers();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{id}",
            produces = "*/*"
    )
    ResponseEntity<KeycloakUser> getUserById(@PathVariable("id") String id);

}
