package tech.astrareal.residential.building;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.account.dto.ChangeTelephoneNumberRequestDto;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.building.dto.BuildingMapper;
import tech.astrareal.residential.building.dto.BuildingRequestDto;
import tech.astrareal.residential.building.dto.BuildingResponseDto;
import tech.astrareal.residential.building.exceptions.BuildingNotFoundException;
import tech.astrareal.residential.project.exceptions.ProjectNotFoundException;
import tech.astrareal.residential.request.Request;
import tech.astrareal.residential.request.dto.RequestMapper;
import tech.astrareal.residential.request.dto.RequestResponseDto;
import tech.astrareal.residential.shared.ChangeNameRequestDto;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.dto.UnitMapper;
import tech.astrareal.residential.unit.dto.UnitResponseDto;

import java.util.UUID;

@RestController
@RequestMapping("/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<BuildingResponseDto>> getBuildings() {
        Page<Building> buildings = buildingService.getBuildings(Pageable.unpaged());

        return ResponseEntity.ok(buildings.map(BuildingMapper.INSTANCE::buildingToBuildingResponseDto));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> createBuilding(@RequestBody @Valid BuildingRequestDto buildingRequestDto) throws ProjectNotFoundException {
        Building building = buildingService.createBuilding(buildingRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> getBuilding(@PathVariable UUID id) throws BuildingNotFoundException {
        Building building = buildingService.getBuilding(id);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @GetMapping("{id}/units")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<UnitResponseDto>> getUnitsOfBuilding(@PathVariable UUID id) throws BuildingNotFoundException {
        Page<Unit> units = buildingService.getUnitsOfBuilding(id, Pageable.unpaged());

        return ResponseEntity.ok(units.map(UnitMapper.INSTANCE::unitToUnitResponseDto));
    }

    @PutMapping("{id}/name")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> setName(@PathVariable UUID id, @RequestBody @Valid ChangeNameRequestDto changeNameRequestDto) throws BuildingNotFoundException {
        Building building = buildingService.setName(id, changeNameRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @PutMapping("{id}/address")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> setAddress(@PathVariable UUID id, @RequestBody @Valid AddressRequestDto addressRequestDto) throws BuildingNotFoundException {
        Building building = buildingService.setAddress(id, addressRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @PutMapping("{id}/telephone_number_office")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> setTelephoneNumberOffice(@PathVariable UUID id, @RequestBody ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingService.setTelephoneNumberOffice(id, changeTelephoneNumberRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @PutMapping("{id}/telephone_number_reception")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> setTelephoneNumberReception(@PathVariable UUID id, @RequestBody ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingService.setTelephoneNumberReception(id, changeTelephoneNumberRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @PutMapping("{id}/telephone_number_security")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<BuildingResponseDto> setTelephoneNumberSecurity(@PathVariable UUID id, @RequestBody ChangeTelephoneNumberRequestDto changeTelephoneNumberRequestDto) throws BuildingNotFoundException {
        Building building = buildingService.setTelephoneNumberSecurity(id, changeTelephoneNumberRequestDto);

        return ResponseEntity.ok(BuildingMapper.INSTANCE.buildingToBuildingResponseDto(building));
    }

    @GetMapping("{id}/requests")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<RequestResponseDto>> getRequestsOfBuilding(@PathVariable UUID id) throws BuildingNotFoundException {
        Page<Request> requests = buildingService.getRequestsOfBuilding(id, Pageable.unpaged());

        return ResponseEntity.ok(requests.map(RequestMapper.INSTANCE::requestToRequestResponseDto));
    }
}
