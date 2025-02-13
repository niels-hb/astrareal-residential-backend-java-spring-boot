package tech.astrareal.residential.building.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.building.Building;

@Mapper
public interface BuildingMapper {
    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);

    BuildingResponseDto buildingToBuildingResponseDto(Building building);
}
