package tech.astrareal.residential.building.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.shared.IdRequestDto;
import tech.astrareal.residential.shared.RegexPatterns;

@Data
public class BuildingRequestDto {
    @NotBlank
    private String name;

    @NotNull
    @Valid
    private AddressRequestDto address;

    @NotNull
    @Valid
    private IdRequestDto project;

    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberOffice;

    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberReception;

    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumberSecurity;
}
