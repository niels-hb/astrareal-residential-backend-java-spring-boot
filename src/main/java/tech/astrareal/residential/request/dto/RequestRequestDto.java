package tech.astrareal.residential.request.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.astrareal.residential.request.RequestType;
import tech.astrareal.residential.shared.IdRequestDto;

@Data
public class RequestRequestDto {
    @NotNull
    private RequestType type;

    @NotNull
    @Valid
    private IdRequestDto unit;
}
