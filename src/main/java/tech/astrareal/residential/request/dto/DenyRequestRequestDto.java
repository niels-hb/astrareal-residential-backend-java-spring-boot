package tech.astrareal.residential.request.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DenyRequestRequestDto {
    @NotBlank
    private String reason;
}
