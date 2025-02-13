package tech.astrareal.residential.account.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.company.Company;
import tech.astrareal.residential.person.Person;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Named("mapToAppropriateAccountType")
    default AccountResponseDto mapToAppropriateAccountType(Account account) {
        if (account == null) {
            return null;
        } else if (account.getPerson() != null) {
            return personToPersonAccountResponseDto(account.getPerson());
        } else if (account.getCompany() != null) {
            return companyToCompanyAccountResponseDto(account.getCompany());
        } else {
            return accountToAccountResponseDto(account);
        }
    }

    AccountResponseDto accountToAccountResponseDto(Account account);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "name", source = "account.name")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "telephoneNumber", source = "account.telephoneNumber")
    CompanyAccountResponseDto companyToCompanyAccountResponseDto(Company company);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "name", source = "account.name")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "telephoneNumber", source = "account.telephoneNumber")
    PersonAccountResponseDto personToPersonAccountResponseDto(Person person);
}
