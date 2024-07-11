package tech.astrareal.residential.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID>, CrudRepository<Project, UUID> {
}
