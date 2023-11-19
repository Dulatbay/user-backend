package techpark.net.keycloak.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import techpark.net.keycloak.client.configuration.ClientConfiguration;

@FeignClient(name = "${keycloak-api.name:keycloak}", url = "${keycloak-api.url:https://localhost:8484/auth/admin/technopark}", configuration = {ClientConfiguration.class})
public interface KeycloakApiClient extends KeycloakApi {
}
