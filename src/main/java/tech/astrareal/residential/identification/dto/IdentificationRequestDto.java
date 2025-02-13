package tech.astrareal.residential.identification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.identification.Identification;

@Data
public class IdentificationRequestDto {
    @NotNull
    private Identification.Type type;

    @NotBlank
    private String idNumber;

    @NotBlank
    private String fullName;
}
