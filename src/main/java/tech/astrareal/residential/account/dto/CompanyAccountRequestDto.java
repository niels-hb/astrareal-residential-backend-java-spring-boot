package tech.astrareal.residential.account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.address.dto.AddressRequestDto;

@Getter
@Setter
public class CompanyAccountRequestDto extends AccountRequestDto {
    @NotBlank
    private String idNumber;

    @NotBlank
    private String taxIdNumber;

    @NotNull
    @Valid
    private AddressRequestDto registeredAddress;
}
