package tech.astrareal.residential.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.address.Address;

@Data
public class AddressRequestDto {
    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    @NotNull
    private Address.Country country;
}
