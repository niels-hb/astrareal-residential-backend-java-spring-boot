package tech.astrareal.residential.request.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FurnitureItemResponseDto {
    private UUID id;
    private String name;
    private int quantity;
    private int width;
    private int height;
    private int depth;
}
