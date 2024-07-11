package tech.astrareal.residential.refreshtoken;

import org.springframework.data.repository.CrudRepository;
import tech.astrareal.residential.account.Account;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    void deleteAllByAccount(Account account);
}
