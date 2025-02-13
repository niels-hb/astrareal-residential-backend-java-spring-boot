package tech.astrareal.residential.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tech.astrareal.residential.shared.RegexPatterns;

@Data
public class AccountRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumber;

    @NotBlank
    @Email(regexp = RegexPatterns.EMAIL)
    private String email;

    @NotBlank
    private String password;
}
