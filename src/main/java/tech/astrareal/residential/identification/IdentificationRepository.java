package tech.astrareal.residential.identification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IdentificationRepository extends PagingAndSortingRepository<Identification, UUID>, CrudRepository<Identification, UUID> {
}
