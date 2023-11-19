package techpark.net.userbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techpark.net.keycloak.client.model.KeycloakUser;
import techpark.net.userbackend.service.UserService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public String getTest(){
        return "test";
    }

    @GetMapping("/{id}")
    public KeycloakUser getById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/everyone")
    public List<KeycloakUser> getUsers(){
        return userService.getAllUsers();
    }

}
