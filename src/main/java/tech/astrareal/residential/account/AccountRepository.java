package tech.astrareal.residential.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID>, PagingAndSortingRepository<Account, UUID> {
    Optional<Account> findByEmailIgnoreCase(String email);

    Optional<Account> findByTelephoneNumber(String telephoneNumber);

    boolean existsByEmail(String email);

    boolean existsByTelephoneNumber(String telephoneNumber);
}
