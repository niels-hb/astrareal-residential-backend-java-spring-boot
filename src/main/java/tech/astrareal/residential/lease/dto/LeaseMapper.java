package tech.astrareal.residential.lease.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.account.dto.AccountMapper;
import tech.astrareal.residential.lease.Lease;
import tech.astrareal.residential.unit.dto.UnitMapper;

@Mapper(uses = {AccountMapper.class, UnitMapper.class})
public interface LeaseMapper {
    LeaseMapper INSTANCE = Mappers.getMapper(LeaseMapper.class);

    @Mapping(source = "lessee", target = "lessee", qualifiedByName = "mapToAppropriateAccountType")
    @Mapping(source = "lessor", target = "lessor", qualifiedByName = "mapToAppropriateAccountType")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "unitToUnitResponseDto")
    LeaseResponseDto leaseToLeaseResponseDto(Lease lease);
}
