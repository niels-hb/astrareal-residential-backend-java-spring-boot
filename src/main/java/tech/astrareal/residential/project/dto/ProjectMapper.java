package tech.astrareal.residential.project.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.astrareal.residential.project.Project;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectResponseDto projectToProjectResponseDto(Project project);
}
