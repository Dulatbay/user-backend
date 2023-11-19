package techpark.net.keycloak.client.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Token implements Serializable {
    @JsonProperty("token")
    public String accessToken;
}
