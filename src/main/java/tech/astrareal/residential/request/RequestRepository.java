package tech.astrareal.residential.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.building.Building;

import java.util.UUID;

public interface RequestRepository extends PagingAndSortingRepository<Request, UUID>, CrudRepository<Request, UUID> {
    Page<Request> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Request> findByUnitBuildingOrderByCreatedAtDesc(Building building, Pageable pageable);

    Page<Request> findByRequestedByOrApprovedByOrExecutedByOrUnitOwnerOrderByCreatedAtDesc(Account requestedBy, Account approvedBy, Account executedBy, Account owner, Pageable pageable);
}
