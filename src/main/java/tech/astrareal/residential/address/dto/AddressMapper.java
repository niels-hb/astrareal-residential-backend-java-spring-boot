package tech.astrareal.residential.address.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.address.Address;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressResponseDto addressToAddressDto(Address address);
}
