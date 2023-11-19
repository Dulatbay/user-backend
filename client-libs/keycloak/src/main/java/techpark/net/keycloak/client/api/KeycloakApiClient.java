package techpark.net.keycloak.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import techpark.net.keycloak.client.configuration.ClientConfiguration;

@FeignClient(name = "${keycloak-api.name:keycloak}", url = "${keycloak-api.url:http://localhost:8484/auth/admin/realms/techno-park}", configuration = {ClientConfiguration.class})
public interface KeycloakApiClient extends KeycloakApi {
}
