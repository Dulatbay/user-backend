package techpark.net.keycloak.client.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import techpark.net.keycloak.client.model.KeycloakUser;

import java.util.List;


@Validated
@Tag(name = "Keycloak", description = "Методы для работы с keycloak API")
public interface KeycloakApi {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users?first=10&max=15",
            produces = "*/*"
    )
    ResponseEntity<List<KeycloakUser>> getAllUsers(@RequestPart("first") int first, @RequestPart("max") int max);
}
