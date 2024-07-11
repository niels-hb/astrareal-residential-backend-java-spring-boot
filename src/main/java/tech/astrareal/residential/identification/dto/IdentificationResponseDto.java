package tech.astrareal.residential.identification.dto;

import lombok.Data;
import tech.astrareal.residential.identification.Identification;

import java.util.UUID;

@Data
public class IdentificationResponseDto {
    private UUID id;
    private Identification.Type type;
    private String idNumber;
    private String fullName;
}
