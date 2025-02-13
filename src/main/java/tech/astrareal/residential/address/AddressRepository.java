package tech.astrareal.residential.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AddressRepository extends PagingAndSortingRepository<Address, UUID>, CrudRepository<Address, UUID> {
}
