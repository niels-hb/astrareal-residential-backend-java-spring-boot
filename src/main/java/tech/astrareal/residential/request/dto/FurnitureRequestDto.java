package tech.astrareal.residential.request.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.request.FurnitureRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FurnitureRequestDto extends RequestRequestDto {
    @NotEmpty
    @Valid
    private List<FurnitureItemRequestDto> items;

    @NotNull
    private Date movingDate;

    @NotNull
    private Date movingTimeFrom;

    @NotNull
    private Date movingTimeTo;

    private List<FurnitureRequest.FurnitureRequirement> requirements;

    private String otherRequirements;

    private String notes;
}
