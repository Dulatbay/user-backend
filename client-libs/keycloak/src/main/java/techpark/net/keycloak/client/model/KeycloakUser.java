package techpark.net.keycloak.client.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class KeycloakUser {

    private String id;
    private long createdTimestamp;
    private String username;
    private boolean enabled;
    private boolean totp;
    private boolean emailVerified;
    private String firstName;
    private String lastName;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private int notBefore;
    private Access access;


    @Data
    public static class Access {
        private boolean manageGroupMembership;
        private boolean view;
        private boolean mapRoles;
        private boolean impersonate;
        private boolean manage;
    }



    @Override
    public String toString() {
        return "KeycloakUser{" +
                "id='" + id + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", totp=" + totp +
                ", emailVerified=" + emailVerified +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", disableableCredentialTypes=" + disableableCredentialTypes +
                ", requiredActions=" + requiredActions +
                ", notBefore=" + notBefore +
                ", access=" + access +
                '}';
    }
}
