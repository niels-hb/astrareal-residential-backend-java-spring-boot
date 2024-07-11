package tech.astrareal.residential.building;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.address.Address;
import tech.astrareal.residential.project.Project;
import tech.astrareal.residential.shared.RegexPatterns;
import tech.astrareal.residential.unit.Unit;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Building {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "building")
    private List<Unit> units;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Project project;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "building_id"), inverseJoinColumns = @JoinColumn(name = "manager_id"))
    private List<Account> managers;

    @Column()
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberOffice;

    @Column()
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberReception;

    @Column()
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberSecurity;
}
