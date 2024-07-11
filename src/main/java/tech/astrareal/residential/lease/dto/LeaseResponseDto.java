package tech.astrareal.residential.lease.dto;

import lombok.Data;
import tech.astrareal.residential.account.dto.AccountResponseDto;
import tech.astrareal.residential.document.dto.DocumentResponseDto;
import tech.astrareal.residential.unit.dto.UnitResponseDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class LeaseResponseDto {
    private UUID id;
    private AccountResponseDto lessor;
    private AccountResponseDto lessee;
    private Date leaseStart;
    private Date leaseEnd;
    private int rentalFee;
    private String leaseTerms;
    private UnitResponseDto unit;
    private List<DocumentResponseDto> documents;
}
