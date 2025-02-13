package tech.astrareal.residential.unit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.building.Building;

import java.util.UUID;

public interface UnitRepository extends PagingAndSortingRepository<Unit, UUID>, CrudRepository<Unit, UUID> {
    Page<Unit> findByOwner(Account account, Pageable pageable);

    Page<Unit> findByBuilding(Building building, Pageable pageable);
}
