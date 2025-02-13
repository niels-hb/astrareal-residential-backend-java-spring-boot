package tech.astrareal.residential.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tech.astrareal.residential.company.Company;
import tech.astrareal.residential.person.Person;
import tech.astrareal.residential.role.Role;
import tech.astrareal.residential.shared.RegexPatterns;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Email(regexp = RegexPatterns.EMAIL)
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "account")
    private Person person;

    @OneToOne(mappedBy = "account")
    private Company company;

    public void addRole(Role role) {
        roles.add(role);
    }
}
