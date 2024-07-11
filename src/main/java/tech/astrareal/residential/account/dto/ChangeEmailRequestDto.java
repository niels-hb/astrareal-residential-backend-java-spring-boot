package tech.astrareal.residential.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tech.astrareal.residential.shared.RegexPatterns;

@Data
public class ChangeEmailRequestDto {
    @NotBlank
    @Email(regexp = RegexPatterns.EMAIL)
    private String email;
}
