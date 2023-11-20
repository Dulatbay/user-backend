package techpark.net.userbackend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "user",schema="schema_tech-park-net")
public class User {
    @Id
    private String keycloakId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "is_premium")
    private boolean isPremium;

    @Column(name = "is_animated_avatar")
    private boolean isAnimatedAvatar;

    @Column(name = "is_private")
    private boolean isPrivate;
}
