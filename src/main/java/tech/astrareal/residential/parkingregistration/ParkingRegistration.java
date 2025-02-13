package tech.astrareal.residential.parkingregistration;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.accesscard.AccessCard;

import java.util.UUID;

@Entity
@Data
public class ParkingRegistration {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    @NotNull
    private AccessCard accessCard;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String plateNumber;

    @Column(nullable = false)
    @NotBlank
    private String brand;

    @Column(nullable = false)
    @NotBlank
    private String model;

    @Column(nullable = false)
    @NotBlank
    private String color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    public enum Type {
        CAR, MOTORBIKE, BICYCLE
    }
}
