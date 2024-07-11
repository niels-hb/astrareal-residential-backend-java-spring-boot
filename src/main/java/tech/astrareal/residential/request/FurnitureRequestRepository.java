package tech.astrareal.residential.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface FurnitureRequestRepository extends PagingAndSortingRepository<FurnitureRequest, UUID>, CrudRepository<FurnitureRequest, UUID> {
}
