package tech.astrareal.residential.address.dto;

import lombok.Data;
import tech.astrareal.residential.address.Address;

import java.util.UUID;

@Data
public class AddressResponseDto {
    private UUID id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private Address.Country country;
}
