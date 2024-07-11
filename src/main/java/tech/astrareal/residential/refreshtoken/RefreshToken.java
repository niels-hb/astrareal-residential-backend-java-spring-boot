package tech.astrareal.residential.refreshtoken;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import tech.astrareal.residential.account.Account;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class RefreshToken {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;
}
