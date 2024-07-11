package tech.astrareal.residential.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequestDto {
    @NotBlank
    private String name;
}
