package tech.astrareal.residential.project.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProjectResponseDto {
    private UUID id;
    private String name;
}
