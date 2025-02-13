package tech.astrareal.residential.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tech.astrareal.residential.shared.RegexPatterns;

@Data
public class ChangeTelephoneNumberRequestDto {
    @NotBlank
    @Pattern(regexp = RegexPatterns.TELEPHONE_NUMBER)
    private String telephoneNumber;
}
