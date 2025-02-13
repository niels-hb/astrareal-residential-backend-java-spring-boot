package tech.astrareal.residential.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String addressLine1;

    @Column()
    private String addressLine2;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    @NotBlank
    private String postalCode;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;

    public enum Country {
        GERMANY, VIETNAM
    }
}
