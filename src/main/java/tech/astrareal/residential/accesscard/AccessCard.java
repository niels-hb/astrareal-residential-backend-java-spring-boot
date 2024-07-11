package tech.astrareal.residential.accesscard;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.parkingregistration.ParkingRegistration;
import tech.astrareal.residential.unit.Unit;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class AccessCard {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String number;

    @OneToMany(mappedBy = "accessCard")
    private List<ParkingRegistration> parkingRegistrations;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Unit unit;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Account owner;
}
