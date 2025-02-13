package tech.astrareal.residential.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.oauth2.OAuth2Response;
import tech.astrareal.residential.refreshtoken.RefreshTokenService;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtUtil {
    private static final String ISSUER = "astrareal.tech";

    @Value("${residential.security.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${residential.security.jwtExpirationInSeconds}")
    private int jwtExpirationInSeconds;

    @Autowired
    private RefreshTokenService refreshTokenService;

    private Algorithm algorithm;

    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC512(jwtSecretKey);
        jwtVerifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public String createJwt(Account account) {
        Date now = new Date();
        Date expiration = DateUtils.addSeconds(now, jwtExpirationInSeconds);

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(account.getId().toString())
                .withAudience("residential.astrareal.tech")
                .withExpiresAt(expiration)
                .withNotBefore(now)
                .withIssuedAt(now)
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public OAuth2Response createOAuth2Response(Account account) {
        return new OAuth2Response(
                createJwt(account),
                refreshTokenService.createRefreshToken(account).getId().toString(),
                jwtExpirationInSeconds
        );
    }

    public DecodedJWT parseJwt(String token) {
        try {
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException exception) {
            log.warn("Invalid JWT: " + exception.getMessage());
            return null;
        }
    }
}
