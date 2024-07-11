package tech.astrareal.residential.project;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.building.Building;
import tech.astrareal.residential.building.dto.BuildingMapper;
import tech.astrareal.residential.building.dto.BuildingResponseDto;
import tech.astrareal.residential.project.dto.ProjectMapper;
import tech.astrareal.residential.project.dto.ProjectRequestDto;
import tech.astrareal.residential.project.dto.ProjectResponseDto;
import tech.astrareal.residential.project.exceptions.ProjectNotFoundException;
import tech.astrareal.residential.shared.ChangeNameRequestDto;

import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<ProjectResponseDto>> getProjects() {
        Page<Project> projects = projectService.getProjects(Pageable.unpaged());

        return ResponseEntity.ok(projects.map(ProjectMapper.INSTANCE::projectToProjectResponseDto));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto) {
        Project project = projectService.createProject(projectRequestDto);

        return ResponseEntity.ok(ProjectMapper.INSTANCE.projectToProjectResponseDto(project));
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable UUID id) throws ProjectNotFoundException {
        Project project = projectService.getProject(id);

        return ResponseEntity.ok(ProjectMapper.INSTANCE.projectToProjectResponseDto(project));
    }

    @GetMapping("{id}/buildings")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<BuildingResponseDto>> getBuildingsOfProject(@PathVariable UUID id) throws ProjectNotFoundException {
        Page<Building> projects = projectService.getBuildingsOfProject(id, Pageable.unpaged());

        return ResponseEntity.ok(projects.map(BuildingMapper.INSTANCE::buildingToBuildingResponseDto));
    }

    @PutMapping("{id}/name")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDto> setName(@PathVariable UUID id, @RequestBody @Valid ChangeNameRequestDto changeNameRequestDto) throws ProjectNotFoundException {
        Project project = projectService.setName(id, changeNameRequestDto);

        return ResponseEntity.ok(ProjectMapper.INSTANCE.projectToProjectResponseDto(project));
    }
}
