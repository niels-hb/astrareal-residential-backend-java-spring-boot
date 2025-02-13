package tech.astrareal.residential.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class FurnitureRequest {
    @Id
    @Column(name = "request_id", nullable = false)
    @NotNull
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "request_id", nullable = false)
    @NotNull
    private Request request;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotEmpty
    private List<FurnitureItem> items;

    @Column(nullable = false)
    @NotNull
    private Date movingDate;

    @Column(nullable = false)
    @NotNull
    private Date movingTimeFrom;

    @Column(nullable = false)
    @NotNull
    private Date movingTimeTo;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "request_id"))
    @Column(name = "requirement", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<FurnitureRequirement> requirements;

    private String otherRequirements;

    private String notes;

    public enum FurnitureRequirement {
        TROLLEY,
        VAN_PARKING,
        CARGO_LIFT,
        OTHER,
    }
}
