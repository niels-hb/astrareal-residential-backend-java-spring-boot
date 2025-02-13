package tech.astrareal.residential.unit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.accesscard.AccessCard;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.building.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Unit {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "unit", fetch = FetchType.EAGER)
    private List<AccessCard> accessCards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Account owner;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Building building;
}
