package tech.astrareal.residential.account.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String telephoneNumber;
}
