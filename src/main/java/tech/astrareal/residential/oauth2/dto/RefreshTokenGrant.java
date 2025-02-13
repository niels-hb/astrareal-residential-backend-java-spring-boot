package tech.astrareal.residential.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenGrant {
    private String refreshToken;
}
