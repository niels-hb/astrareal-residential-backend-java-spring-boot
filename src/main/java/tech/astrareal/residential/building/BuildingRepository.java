package tech.astrareal.residential.building;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tech.astrareal.residential.project.Project;

import java.util.UUID;

public interface BuildingRepository extends PagingAndSortingRepository<Building, UUID>, CrudRepository<Building, UUID> {
    Page<Building> findByProject(Project project, Pageable pageable);
}
