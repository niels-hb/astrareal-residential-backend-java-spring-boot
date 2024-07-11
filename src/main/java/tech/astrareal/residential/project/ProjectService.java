package tech.astrareal.residential.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.building.Building;
import tech.astrareal.residential.building.BuildingRepository;
import tech.astrareal.residential.project.dto.ProjectRequestDto;
import tech.astrareal.residential.project.exceptions.ProjectNotFoundException;
import tech.astrareal.residential.shared.ChangeNameRequestDto;

import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public Page<Project> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Project createProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setName(projectRequestDto.getName());

        return projectRepository.save(project);
    }

    public Project getProject(UUID id) throws ProjectNotFoundException {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    public Page<Building> getBuildingsOfProject(UUID id, Pageable pageable) throws ProjectNotFoundException {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);

        return buildingRepository.findByProject(project, pageable);
    }

    public Project setName(UUID id, ChangeNameRequestDto changeNameRequestDto) throws ProjectNotFoundException {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);

        project.setName(changeNameRequestDto.getName());

        return projectRepository.save(project);
    }
}
