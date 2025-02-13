package tech.astrareal.residential.lease;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.lease.dto.LeaseMapper;
import tech.astrareal.residential.lease.dto.LeaseRequestDto;
import tech.astrareal.residential.lease.dto.LeaseResponseDto;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

@RestController
@RequestMapping("/leases")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<LeaseResponseDto>> getLeases() {
        Page<Lease> leases = leaseService.getLeases(Pageable.unpaged());

        return ResponseEntity.ok(leases.map(LeaseMapper.INSTANCE::leaseToLeaseResponseDto));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasRole('OWNER')")
    public ResponseEntity<LeaseResponseDto> createLease(@RequestBody @Valid LeaseRequestDto leaseRequestDto) throws UnitNotFoundException, AccountNotFoundException {
        Lease lease = leaseService.createLease(leaseRequestDto);

        return ResponseEntity.ok(LeaseMapper.INSTANCE.leaseToLeaseResponseDto(lease));
    }
}
