package tech.astrareal.residential.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.account.AccountRepository;
import tech.astrareal.residential.oauth2.dto.RefreshTokenGrant;
import tech.astrareal.residential.oauth2.dto.ResourceOwnerPasswordCredentialsGrant;
import tech.astrareal.residential.oauth2.exceptions.InvalidCredentialsException;
import tech.astrareal.residential.refreshtoken.RefreshToken;
import tech.astrareal.residential.refreshtoken.RefreshTokenService;
import tech.astrareal.residential.security.JwtUtil;

import java.util.UUID;

@Service
public class OAuth2Service {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public OAuth2Response getToken(ResourceOwnerPasswordCredentialsGrant grant) throws InvalidCredentialsException {
        Account account = accountRepository.findByEmailIgnoreCase(grant.getUsername())
                .or(() -> accountRepository.findByTelephoneNumber(grant.getUsername()))
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(grant.getPassword(), account.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return jwtUtil.createOAuth2Response(account);
    }

    public OAuth2Response getToken(RefreshTokenGrant grant) throws InvalidCredentialsException {
        Account account = refreshTokenService.rotateRefreshToken(UUID.fromString(grant.getRefreshToken()))
                .map(RefreshToken::getAccount)
                .orElseThrow(InvalidCredentialsException::new);

        return jwtUtil.createOAuth2Response(account);
    }
}
