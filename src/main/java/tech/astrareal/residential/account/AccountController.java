package tech.astrareal.residential.account;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.account.dto.*;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.account.exceptions.DuplicateAccountException;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.address.exceptions.AddressNotFoundException;
import tech.astrareal.residential.company.Company;
import tech.astrareal.residential.lease.Lease;
import tech.astrareal.residential.lease.dto.LeaseMapper;
import tech.astrareal.residential.lease.dto.LeaseResponseDto;
import tech.astrareal.residential.oauth2.exceptions.InvalidCredentialsException;
import tech.astrareal.residential.person.Person;
import tech.astrareal.residential.request.Request;
import tech.astrareal.residential.request.dto.RequestMapper;
import tech.astrareal.residential.request.dto.RequestResponseDto;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.dto.UnitMapper;
import tech.astrareal.residential.unit.dto.UnitResponseDto;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<AccountResponseDto>> getAccounts() {
        Page<Account> accounts = accountService.getAccounts(Pageable.unpaged());

        return ResponseEntity.ok(accounts.map(AccountMapper.INSTANCE::mapToAppropriateAccountType));
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and (principal.username == #id.toString() or hasRole('ADMIN'))")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable UUID id) throws AccountNotFoundException {
        Account account = accountService.getAccountById(id);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @PostMapping("/personal")
    public ResponseEntity<PersonAccountResponseDto> createPersonalAccount(
            @RequestBody @Valid PersonAccountRequestDto personalAccountRequestDto
    ) throws DuplicateAccountException {
        Person person = accountService.createPersonalAccount(personalAccountRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.personToPersonAccountResponseDto(person));
    }

    @PostMapping("/company")
    public ResponseEntity<CompanyAccountResponseDto> createCompanyAccount(
            @RequestBody @Valid CompanyAccountRequestDto companyAccountRequestDto
    ) throws DuplicateAccountException {
        Company company = accountService.createCompanyAccount(companyAccountRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.companyToCompanyAccountResponseDto(company));
    }

    @PutMapping("{id}/address")
    @PreAuthorize("isAuthenticated() and principal.username == #id.toString()")
    public ResponseEntity<AccountResponseDto> setAddress(@PathVariable UUID id, @RequestBody @Valid AddressRequestDto addressRequestDto) throws AddressNotFoundException, AccountNotFoundException {
        Account account = accountService.setAddress(id, addressRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @PutMapping("{id}/email")
    @PreAuthorize("isAuthenticated() and principal.username == #id.toString()")
    public ResponseEntity<AccountResponseDto> setEmail(@PathVariable UUID id, @RequestBody @Valid ChangeEmailRequestDto changeEmailRequestDto) throws AccountNotFoundException {
        Account account = accountService.setEmail(id, changeEmailRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @PutMapping("{id}/telephone_number")
    @PreAuthorize("isAuthenticated() and principal.username == #id.toString()")
    public ResponseEntity<AccountResponseDto> setTelephoneNumber(@PathVariable UUID id, @RequestBody @Valid ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws AccountNotFoundException {
        Account account = accountService.setTelephoneNumber(id, changeTelephoneNumberRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @PutMapping("{id}/password")
    @PreAuthorize("isAuthenticated() and principal.username == #id.toString()")
    public ResponseEntity<AccountResponseDto> setPassword(@PathVariable UUID id, @RequestBody @Valid ChangePasswordRequestDto changePasswordRequestDto) throws AccountNotFoundException, InvalidCredentialsException {
        Account account = accountService.setPassword(id, changePasswordRequestDto);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> deleteAccount(@PathVariable UUID id) throws AccountNotFoundException {
        Account account = accountService.deleteAccount(id);

        return ResponseEntity.ok(AccountMapper.INSTANCE.mapToAppropriateAccountType(account));
    }

    @GetMapping("{id}/units")
    @PreAuthorize("isAuthenticated() and (principal.username == #id.toString() or hasRole('ADMIN'))")
    public ResponseEntity<Page<UnitResponseDto>> getOwnedUnits(@PathVariable UUID id) throws AccountNotFoundException {
        Page<Unit> units = accountService.getOwnedUnits(id, Pageable.unpaged());

        return ResponseEntity.ok(units.map(UnitMapper.INSTANCE::unitToUnitResponseDto));
    }

    @GetMapping("{id}/leases")
    @PreAuthorize("isAuthenticated() and (principal.username == #id.toString() or hasRole('ADMIN'))")
    public ResponseEntity<Page<LeaseResponseDto>> getLeases(@PathVariable UUID id) throws AccountNotFoundException {
        Page<Lease> leases = accountService.getLeases(id, Pageable.unpaged());

        return ResponseEntity.ok(leases.map(LeaseMapper.INSTANCE::leaseToLeaseResponseDto));
    }

    @GetMapping("{id}/requests")
    @PreAuthorize("isAuthenticated() and (principal.username == #id.toString() or hasRole('ADMIN'))")
    public ResponseEntity<Page<RequestResponseDto>> getRequests(@PathVariable UUID id) throws AccountNotFoundException {
        Page<Request> requests = accountService.getRequests(id, Pageable.unpaged());

        return ResponseEntity.ok(requests.map(RequestMapper.INSTANCE::requestToRequestResponseDto));
    }
}
