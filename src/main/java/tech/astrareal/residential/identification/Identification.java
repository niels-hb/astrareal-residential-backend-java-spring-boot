package tech.astrareal.residential.identification;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.person.Person;

import java.util.UUID;

@Entity
@Data
public class Identification {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "identification")
    private Person person;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String idNumber;

    @Column(nullable = false)
    @NotBlank
    private String fullName;

    public enum Type {
        PASSPORT, ID_CARD
    }
}
