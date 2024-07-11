package tech.astrareal.residential.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tech.astrareal.residential.building.Building;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "project")
    private List<Building> buildings;
}
