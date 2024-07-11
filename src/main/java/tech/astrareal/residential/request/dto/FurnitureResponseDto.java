package tech.astrareal.residential.request.dto;

import lombok.Getter;
import lombok.Setter;
import tech.astrareal.residential.request.FurnitureRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FurnitureResponseDto extends RequestResponseDto {
    private List<FurnitureItemResponseDto> items;
    private Date movingDate;
    private Date movingTimeFrom;
    private Date movingTimeTo;
    private List<FurnitureRequest.FurnitureRequirement> requirements;
    private String otherRequirements;
    private String notes;
}
