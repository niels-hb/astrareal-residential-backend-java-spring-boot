package tech.astrareal.residential.request.dto;

import lombok.Data;
import tech.astrareal.residential.account.dto.AccountResponseDto;
import tech.astrareal.residential.request.RequestType;
import tech.astrareal.residential.unit.dto.UnitResponseDto;

import java.util.UUID;

@Data
public class RequestResponseDto {
    private UUID id;
    private RequestType type;
    private UnitResponseDto unit;
    private AccountResponseDto requestedBy;
    private AccountResponseDto approvedBy;
    private AccountResponseDto executedBy;
    private boolean denied;
    private String denialReason;
}
