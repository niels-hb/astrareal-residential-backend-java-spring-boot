package tech.astrareal.residential.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.address.Address;
import tech.astrareal.residential.identification.Identification;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Person {
    @Id
    @Column(name = "account_id", nullable = false)
    @NotNull
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull
    private Account account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Nationality nationality;

    @Column(nullable = false)
    @NotNull
    private Date dateOfBirth;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Address permanentResidentialAddress;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Identification identification;

    public enum Gender {
        MALE, FEMALE
    }

    public enum Nationality {
        GERMAN, VIETNAMESE
    }
}
