package tech.astrareal.residential.oauth2;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.astrareal.residential.oauth2.dto.RefreshTokenGrant;
import tech.astrareal.residential.oauth2.dto.ResourceOwnerPasswordCredentialsGrant;
import tech.astrareal.residential.oauth2.exceptions.InvalidCredentialsException;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
    @Autowired
    private OAuth2Service securityService;

    @PostMapping(
            value = "/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "grant_type=password"
    )
    public ResponseEntity<OAuth2Response> useResourceOwnerPasswordCredentialsGrant(
            @NotBlank String username,
            @NotBlank String password
    ) throws InvalidCredentialsException {
        return ResponseEntity.ok(
                securityService.getToken(new ResourceOwnerPasswordCredentialsGrant(username, password))
        );
    }

    @PostMapping(
            value = "/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "grant_type=refresh_token"
    )
    public ResponseEntity<OAuth2Response> useRefreshTokenGrant(
            @NotBlank String refresh_token
    ) throws InvalidCredentialsException {
        return ResponseEntity.ok(
                securityService.getToken(new RefreshTokenGrant(refresh_token))
        );
    }
}
