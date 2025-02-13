package tech.astrareal.residential.request.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FurnitureItemRequestDto {
    @NotBlank
    private String name;

    @Min(1)
    private int quantity;

    @Min(1)
    private int width;

    @Min(1)
    private int height;

    @Min(1)
    private int depth;
}
