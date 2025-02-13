package tech.astrareal.residential.request.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.account.dto.AccountMapper;
import tech.astrareal.residential.request.FurnitureRequest;
import tech.astrareal.residential.request.Request;
import tech.astrareal.residential.unit.dto.UnitMapper;

@Mapper(uses = {AccountMapper.class, UnitMapper.class})
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    default RequestResponseDto requestToRequestResponseDto(Request request) {
        if (request.getFurnitureRequest() != null) {
            return furnitureRequestToFurnitureResponseDto(request.getFurnitureRequest());
        } else {
            throw new IllegalArgumentException("Unknown request type");
        }
    }

    @Mapping(target = "type", source = "request.type")
    @Mapping(target = "unit", source = "request.unit", qualifiedByName = "unitToUnitResponseDto")
    @Mapping(target = "requestedBy", source = "request.requestedBy", qualifiedByName = "mapToAppropriateAccountType")
    @Mapping(target = "approvedBy", source = "request.approvedBy", qualifiedByName = "mapToAppropriateAccountType")
    @Mapping(target = "executedBy", source = "request.executedBy", qualifiedByName = "mapToAppropriateAccountType")
    @Mapping(target = "denied", source = "request.denied")
    @Mapping(target = "denialReason", source = "request.denialReason")
    FurnitureResponseDto furnitureRequestToFurnitureResponseDto(FurnitureRequest furnitureRequest);
}
