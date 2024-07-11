package tech.astrareal.residential.lease;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.document.Document;
import tech.astrareal.residential.unit.Unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Lease {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Account lessor;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Account lessee;

    @Column(nullable = false)
    @NotNull
    private Date leaseStart;

    @Column(nullable = false)
    @NotNull
    private Date leaseEnd;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Integer rentalFee;

    @Column()
    private String leaseTerms;

    @OneToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Unit unit;

    @OneToMany(mappedBy = "lease", fetch = FetchType.EAGER)
    private List<Document> documents = new ArrayList<>();
}
