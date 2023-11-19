package techpark.net.userbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"techpark.net.keycloak.client.api"})
public class UserBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBackendApplication.class, args);
	}

}
