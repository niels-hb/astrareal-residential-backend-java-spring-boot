package tech.astrareal.residential.shared;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeNameRequestDto {
    @NotBlank
    private String name;
}
