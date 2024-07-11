package tech.astrareal.residential.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class FurnitureItem {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @NotNull
    private FurnitureRequest request;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @Min(1)
    private int quantity;

    @Column(nullable = false)
    @Min(1)
    private int width;

    @Column(nullable = false)
    @Min(1)
    private int height;

    @Column(nullable = false)
    @Min(1)
    private int depth;
}
