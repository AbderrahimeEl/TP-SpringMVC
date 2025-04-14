package net.elm.tpspringmvc.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Data @Builder
public class AppRole{
    @Id
    private String role;
}
