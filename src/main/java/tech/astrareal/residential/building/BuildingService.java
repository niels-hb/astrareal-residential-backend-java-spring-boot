package tech.astrareal.residential.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.account.dto.ChangeTelephoneNumberRequestDto;
import tech.astrareal.residential.address.Address;
import tech.astrareal.residential.address.AddressService;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.building.dto.BuildingRequestDto;
import tech.astrareal.residential.building.exceptions.BuildingNotFoundException;
import tech.astrareal.residential.project.Project;
import tech.astrareal.residential.project.ProjectRepository;
import tech.astrareal.residential.project.exceptions.ProjectNotFoundException;
import tech.astrareal.residential.request.Request;
import tech.astrareal.residential.request.RequestRepository;
import tech.astrareal.residential.shared.ChangeNameRequestDto;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.UnitRepository;

import java.util.UUID;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RequestRepository requestRepository;

    public Page<Building> getBuildings(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }

    public Building createBuilding(BuildingRequestDto buildingRequestDto) throws ProjectNotFoundException {
        Address address = addressService.createAddress(buildingRequestDto.getAddress());
        Project project = projectRepository.findById(buildingRequestDto.getProject().getId()).orElseThrow(ProjectNotFoundException::new);

        Building building = new Building();
        building.setName(buildingRequestDto.getName());
        building.setAddress(address);
        building.setProject(project);
        building.setTelephoneNumberOffice(buildingRequestDto.getTelephoneNumberOffice());
        building.setTelephoneNumberReception(buildingRequestDto.getTelephoneNumberReception());
        building.setTelephoneNumberSecurity(buildingRequestDto.getTelephoneNumberSecurity());

        return buildingRepository.save(building);
    }

    public Building getBuilding(UUID id) throws BuildingNotFoundException {
        return buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);
    }

    public Page<Unit> getUnitsOfBuilding(UUID id, Pageable pageable) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        return unitRepository.findByBuilding(building, pageable);
    }

    public Building setName(UUID id, ChangeNameRequestDto changeNameRequestDto) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        building.setName(changeNameRequestDto.getName());

        return buildingRepository.save(building);
    }

    public Building setAddress(UUID id, AddressRequestDto addressRequestDto) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);
        Address address = addressService.createAddress(addressRequestDto);

        building.setAddress(address);

        return buildingRepository.save(building);
    }

    public Building setTelephoneNumberOffice(UUID id, ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        building.setTelephoneNumberOffice(changeTelephoneNumberRequestDto.getTelephoneNumber());

        return buildingRepository.save(building);
    }

    public Building setTelephoneNumberReception(UUID id, ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        building.setTelephoneNumberReception(changeTelephoneNumberRequestDto.getTelephoneNumber());

        return buildingRepository.save(building);
    }

    public Building setTelephoneNumberSecurity(UUID id, ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        building.setTelephoneNumberSecurity(changeTelephoneNumberRequestDto.getTelephoneNumber());

        return buildingRepository.save(building);
    }

    public Page<Request> getRequestsOfBuilding(UUID id, Pageable pageable) throws BuildingNotFoundException {
        Building building = buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new);

        return requestRepository.findByUnitBuildingOrderByCreatedAtDesc(building, pageable);
    }
}
