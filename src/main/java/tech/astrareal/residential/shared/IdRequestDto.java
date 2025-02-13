package tech.astrareal.residential.shared;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class IdRequestDto {
    @NotNull
    private UUID id;
}
