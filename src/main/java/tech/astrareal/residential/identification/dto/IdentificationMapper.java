package tech.astrareal.residential.identification.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.identification.Identification;

@Mapper
public interface IdentificationMapper {
    IdentificationMapper INSTANCE = Mappers.getMapper(IdentificationMapper.class);

    IdentificationResponseDto identificationToIdentificationDto(Identification identification);
}
