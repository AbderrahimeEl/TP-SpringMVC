package net.elm.tpspringmvc.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "PATIENTS")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "must not be null")
    @NotBlank
    private String firstname;
    @NotEmpty(message = "must not be null")
    @NotBlank
    private String lastName;
    @DecimalMin(value = "100",message = "score must be les than 100")
    private Integer score;
    private Boolean sick;
}
