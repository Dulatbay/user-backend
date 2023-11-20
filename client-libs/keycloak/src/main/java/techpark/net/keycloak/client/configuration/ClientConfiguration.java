package techpark.net.keycloak.client.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {

    @Value("${authentication.security.keycloak.username:admin}")
    private String bearerAuthUsername;

    @Value("${authentication.security.keycloak.password:admin_password}")
    private String bearerAuthPassword;

    @Value("${authentication.security.keycloak.url:http://localhost:8484/auth/realms/techno-park/protocol/openid-connect/token}")
    private String bearerUrl;

    @Value("${authentication.security.keycloak.client-id:techno-park}")
    private String bearerClientId;

    @Value("${authentication.security.keycloak.client-id:StOF38GKmmCD0w8Sq2bj7u3Y1iClztWr}")
    private String bearerClientSecret;

//    private String currentToken; TODO: сделать так, чтобы если токен доступный, он не шел на api depot

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Token token = getToken();

            requestTemplate.header("Authorization", "Bearer " + token.accessToken);
        };
    }

    private Token getToken() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> vars = new LinkedMultiValueMap<>();
        vars.add("client_id", bearerClientId);
        vars.add("username", bearerAuthUsername);
        vars.add("password", bearerAuthPassword);
        vars.add("client_secret", bearerClientSecret);
        vars.add("grant_type", "password");

        var requestEntity = new HttpEntity<>(vars, headers);
        var template = new RestTemplate();
        var request = template.exchange(
                bearerUrl,
                HttpMethod.POST,
                requestEntity,
                Token.class
        );
        return request.getBody();
    }
}
