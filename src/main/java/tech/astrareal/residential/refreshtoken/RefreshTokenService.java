package tech.astrareal.residential.refreshtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.astrareal.residential.account.Account;

import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> rotateRefreshToken(UUID uuid) {
        return refreshTokenRepository.findById(uuid)
                .map(refreshToken -> {
                    refreshTokenRepository.delete(refreshToken);

                    return refreshToken;
                });
    }

    public RefreshToken createRefreshToken(Account account) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(account);

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public void invalidateRefreshTokensForAccount(Account account) {
        refreshTokenRepository.deleteAllByAccount(account);
    }
}
