package tech.astrareal.residential.unit.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.account.dto.AccountMapper;
import tech.astrareal.residential.unit.Unit;

@Mapper(uses = AccountMapper.class)
public interface UnitMapper {
    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    @Named("unitToUnitResponseDto")
    @Mapping(target = "owner", source = "owner", qualifiedByName = "mapToAppropriateAccountType")
    UnitResponseDto unitToUnitResponseDto(Unit unit);
}
