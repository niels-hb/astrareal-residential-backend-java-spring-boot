package tech.astrareal.residential.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CompanyRepository extends PagingAndSortingRepository<Company, UUID>, CrudRepository<Company, UUID> {
}
