package tech.astrareal.residential.company;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.address.Address;

import java.util.UUID;

@Entity
@Data
public class Company {
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
    @NotBlank
    private String idNumber;

    @Column(nullable = false)
    @NotBlank
    private String taxIdNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Address registeredAddress;
}
