package tech.astrareal.residential.role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Role {
    public static final Role ADMIN = new Role(UUID.fromString("1868c091-fe50-489c-a265-39a3bc9495bf"), "ADMIN");
    public static final Role OWNER = new Role(UUID.fromString("e21bf5b7-7bb3-4757-a59c-8a3e3f73cf93"), "OWNER");

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;
}
