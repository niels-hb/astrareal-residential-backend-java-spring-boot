package tech.astrareal.residential.lease.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tech.astrareal.residential.account.dto.AccountEmailRequestDto;
import tech.astrareal.residential.shared.IdRequestDto;

import java.util.Date;

@Data
public class LeaseRequestDto {
    @NotNull
    @Valid
    private AccountEmailRequestDto lessee;

    @NotNull
    @Valid
    private IdRequestDto lessor;

    @NotNull
    @Valid
    private IdRequestDto unit;

    @NotNull
    private Date leaseStart;

    @NotNull
    private Date leaseEnd;

    @NotNull
    @Positive
    private Integer rentalFee;

    private String leaseTerms;
}
