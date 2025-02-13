package tech.astrareal.residential.account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.identification.dto.IdentificationRequestDto;
import tech.astrareal.residential.person.Person;

import java.util.Date;

@Getter
@Setter
public class PersonAccountRequestDto extends AccountRequestDto {
    @NotNull
    private Person.Nationality nationality;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Person.Gender gender;

    @NotNull
    @Valid
    private AddressRequestDto permanentResidentialAddress;

    @NotNull
    @Valid
    private IdentificationRequestDto identification;
}
