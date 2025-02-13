package tech.astrareal.residential.lease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.account.AccountRepository;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.lease.dto.LeaseRequestDto;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.UnitRepository;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UnitRepository unitRepository;

    public Page<Lease> getLeases(Pageable pageable) {
        return leaseRepository.findAll(pageable);
    }

    public Lease createLease(LeaseRequestDto leaseRequestDto) throws AccountNotFoundException, UnitNotFoundException {
        Account lessee = accountRepository.findByEmailIgnoreCase(leaseRequestDto.getLessee().getEmail()).orElseThrow(AccountNotFoundException::new);
        Account lessor = accountRepository.findById(leaseRequestDto.getLessor().getId()).orElseThrow(AccountNotFoundException::new);
        Unit unit = unitRepository.findById(leaseRequestDto.getUnit().getId()).orElseThrow(UnitNotFoundException::new);

        Lease lease = new Lease();
        lease.setLessee(lessee);
        lease.setLessor(lessor);
        lease.setUnit(unit);
        lease.setLeaseStart(leaseRequestDto.getLeaseStart());
        lease.setLeaseEnd(leaseRequestDto.getLeaseEnd());
        lease.setRentalFee(leaseRequestDto.getRentalFee());
        lease.setLeaseTerms(leaseRequestDto.getLeaseTerms());

        return leaseRepository.save(lease);
    }
}
