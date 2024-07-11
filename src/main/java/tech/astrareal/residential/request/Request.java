package tech.astrareal.residential.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.unit.Unit;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private RequestType type;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Unit unit;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Account requestedBy;

    @ManyToOne
    @JoinColumn
    private Account approvedBy;

    @ManyToOne
    @JoinColumn
    private Account executedBy;

    @Column(nullable = false)
    private boolean denied = false;

    @Column
    private String denialReason;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private FurnitureRequest furnitureRequest;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;
}
