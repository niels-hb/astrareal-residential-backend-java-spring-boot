package tech.astrareal.residential.building.dto;

import lombok.Data;
import tech.astrareal.residential.address.dto.AddressResponseDto;
import tech.astrareal.residential.project.dto.ProjectResponseDto;

import java.util.UUID;

@Data
public class BuildingResponseDto {
    private UUID id;
    private String name;
    private AddressResponseDto address;
    private ProjectResponseDto project;
    private String telephoneNumberOffice;
    private String telephoneNumberReception;
    private String telephoneNumberSecurity;
}
