package tech.astrareal.residential.unit.dto;

import lombok.Data;
import tech.astrareal.residential.accesscard.dto.AccessCardResponseDto;
import tech.astrareal.residential.account.dto.AccountResponseDto;
import tech.astrareal.residential.building.dto.BuildingResponseDto;

import java.util.List;
import java.util.UUID;

@Data
public class UnitResponseDto {
    private UUID id;
    private String name;
    private List<AccessCardResponseDto> accessCards;
    private AccountResponseDto owner;
    private BuildingResponseDto building;
}
