package tech.astrareal.residential.account.dto;

import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.account.AccountType;
import tech.astrareal.residential.address.dto.AddressResponseDto;

@Getter
@Setter
public class CompanyAccountResponseDto extends AccountResponseDto {
    private final AccountType type = AccountType.COMPANY;

    private String idNumber;

    private String taxIdNumber;

    private AddressResponseDto registeredAddress;
}
