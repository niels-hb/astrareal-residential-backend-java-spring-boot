package tech.astrareal.residential.unit;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.building.exceptions.BuildingNotFoundException;
import tech.astrareal.residential.shared.ChangeNameRequestDto;
import tech.astrareal.residential.unit.dto.UnitMapper;
import tech.astrareal.residential.unit.dto.UnitRequestDto;
import tech.astrareal.residential.unit.dto.UnitResponseDto;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/units")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<UnitResponseDto>> getUnits() {
        Page<Unit> units = unitService.getUnits(Pageable.unpaged());

        return ResponseEntity.ok(units.map(UnitMapper.INSTANCE::unitToUnitResponseDto));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<UnitResponseDto> createUnit(@RequestBody @Valid UnitRequestDto unitRequestDto) throws BuildingNotFoundException, AccountNotFoundException {
        Unit unit = unitService.createUnit(unitRequestDto);

        return ResponseEntity.ok(UnitMapper.INSTANCE.unitToUnitResponseDto(unit));
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<UnitResponseDto> getUnit(@PathVariable UUID id) throws UnitNotFoundException {
        Unit unit = unitService.getUnit(id);

        return ResponseEntity.ok(UnitMapper.INSTANCE.unitToUnitResponseDto(unit));
    }

    @PutMapping("{id}/name")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<UnitResponseDto> setName(@PathVariable UUID id, @RequestBody @Valid ChangeNameRequestDto changeNameRequestDto) throws UnitNotFoundException {
        Unit unit = unitService.setName(id, changeNameRequestDto);

        return ResponseEntity.ok(UnitMapper.INSTANCE.unitToUnitResponseDto(unit));
    }
}
