package tech.astrareal.residential.account.dto;

import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.account.AccountType;
import tech.astrareal.residential.address.dto.AddressResponseDto;
import tech.astrareal.residential.identification.dto.IdentificationResponseDto;
import tech.astrareal.residential.person.Person;

import java.util.Date;

@Getter
@Setter
public class PersonAccountResponseDto extends AccountResponseDto {
    private final AccountType type = AccountType.INDIVIDUAL;
    private Person.Nationality nationality;
    private Date dateOfBirth;
    private Person.Gender gender;
    private AddressResponseDto permanentResidentialAddress;
    private IdentificationResponseDto identification;
}
