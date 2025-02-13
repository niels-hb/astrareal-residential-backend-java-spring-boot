package tech.astrareal.residential.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.astrareal.residential.account.dto.*;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.account.exceptions.DuplicateAccountException;
import tech.astrareal.residential.address.Address;
import tech.astrareal.residential.address.AddressService;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.address.exceptions.AddressNotFoundException;
import tech.astrareal.residential.company.Company;
import tech.astrareal.residential.company.CompanyRepository;
import tech.astrareal.residential.identification.Identification;
import tech.astrareal.residential.identification.IdentificationService;
import tech.astrareal.residential.lease.Lease;
import tech.astrareal.residential.lease.LeaseRepository;
import tech.astrareal.residential.oauth2.exceptions.InvalidCredentialsException;
import tech.astrareal.residential.person.Person;
import tech.astrareal.residential.person.PersonRepository;
import tech.astrareal.residential.refreshtoken.RefreshTokenService;
import tech.astrareal.residential.request.Request;
import tech.astrareal.residential.request.RequestRepository;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.UnitRepository;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private IdentificationService identificationService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Account> getAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account getAccountById(UUID id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    public Account createAccount(
            AccountRequestDto accountRequestDto
    ) throws DuplicateAccountException {
        if (accountRepository.existsByEmail(accountRequestDto.getEmail()) ||
                accountRepository.existsByTelephoneNumber(accountRequestDto.getTelephoneNumber())) {
            throw new DuplicateAccountException();
        }

        Account account = new Account();
        account.setName(accountRequestDto.getName());
        account.setTelephoneNumber(accountRequestDto.getTelephoneNumber());
        account.setEmail(accountRequestDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountRequestDto.getPassword()));

        return accountRepository.save(account);
    }

    @Transactional
    public Person createPersonalAccount(
            PersonAccountRequestDto personalAccountRequestDto
    ) throws DuplicateAccountException {
        Account account = createAccount(personalAccountRequestDto);
        Identification identification = identificationService.createIdentification(personalAccountRequestDto.getIdentification());
        Address address = addressService.createAddress(personalAccountRequestDto.getPermanentResidentialAddress());

        Person person = new Person();
        person.setAccount(account);
        person.setDateOfBirth(personalAccountRequestDto.getDateOfBirth());
        person.setGender(personalAccountRequestDto.getGender());
        person.setNationality(personalAccountRequestDto.getNationality());
        person.setIdentification(identification);
        person.setPermanentResidentialAddress(address);

        return personRepository.save(person);
    }

    @Transactional
    public Company createCompanyAccount(
            CompanyAccountRequestDto companyAccountRequestDto
    ) throws DuplicateAccountException {
        Account account = createAccount(companyAccountRequestDto);
        Address address = addressService.createAddress(companyAccountRequestDto.getRegisteredAddress());

        Company company = new Company();
        company.setAccount(account);
        company.setIdNumber(companyAccountRequestDto.getIdNumber());
        company.setTaxIdNumber(companyAccountRequestDto.getTaxIdNumber());
        company.setRegisteredAddress(address);

        return companyRepository.save(company);
    }

    public Account setAddress(UUID id, AddressRequestDto addressRequestDto) throws AccountNotFoundException, AddressNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        if (account.getPerson() != null) {
            Person person = account.getPerson();
            person.setPermanentResidentialAddress(addressService.updateAddress(person.getPermanentResidentialAddress().getId(), addressRequestDto));

            return person.getAccount();
        } else if (account.getCompany() != null) {
            Company company = account.getCompany();
            company.setRegisteredAddress(addressService.updateAddress(company.getRegisteredAddress().getId(), addressRequestDto));

            return company.getAccount();
        } else {
            throw new IllegalArgumentException("Account type is not yet implemented");
        }
    }

    public Account setEmail(UUID id, ChangeEmailRequestDto changeEmailRequestDto) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        account.setEmail(changeEmailRequestDto.getEmail());

        return accountRepository.save(account);
    }

    public Account setTelephoneNumber(UUID id, ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        account.setTelephoneNumber(changeTelephoneNumberRequestDto.getTelephoneNumber());

        return accountRepository.save(account);
    }

    public Account setPassword(UUID id, ChangePasswordRequestDto changePasswordRequestDto) throws AccountNotFoundException, InvalidCredentialsException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), account.getPassword())) {
            throw new InvalidCredentialsException();
        }

        refreshTokenService.invalidateRefreshTokensForAccount(account);

        account.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));

        return accountRepository.save(account);
    }

    public Account deleteAccount(UUID id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        accountRepository.delete(account);

        return account;
    }

    public Page<Unit> getOwnedUnits(UUID id, Pageable pageable) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        return unitRepository.findByOwner(account, pageable);
    }

    public Page<Lease> getLeases(UUID id, Pageable pageable) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        return leaseRepository.findByLesseeOrLessor(account, account, pageable);
    }

    public Page<Request> getRequests(UUID id, Pageable pageable) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        return requestRepository.findByRequestedByOrApprovedByOrExecutedByOrUnitOwnerOrderByCreatedAtDesc(account, account, account, account, pageable);
    }
}
