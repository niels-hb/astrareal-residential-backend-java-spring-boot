package tech.astrareal.residential.lease;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tech.astrareal.residential.account.Account;

import java.util.UUID;

public interface LeaseRepository extends PagingAndSortingRepository<Lease, UUID>, CrudRepository<Lease, UUID> {
    Page<Lease> findByLesseeOrLessor(Account lessee, Account lessor, Pageable pageable);
}
