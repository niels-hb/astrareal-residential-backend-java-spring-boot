package tech.astrareal.residential.unit.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.account.dto.AccountEmailRequestDto;
import tech.astrareal.residential.shared.IdRequestDto;

@Data
public class UnitRequestDto {
    @NotNull
    @Valid
    private IdRequestDto building;

    @NotNull
    @Valid
    private AccountEmailRequestDto owner;

    @NotBlank
    private String name;
}
