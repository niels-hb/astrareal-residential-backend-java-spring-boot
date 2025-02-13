package tech.astrareal.residential.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.account.AccountRepository;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.building.Building;
import tech.astrareal.residential.building.BuildingRepository;
import tech.astrareal.residential.building.exceptions.BuildingNotFoundException;
import tech.astrareal.residential.role.Role;
import tech.astrareal.residential.shared.ChangeNameRequestDto;
import tech.astrareal.residential.unit.dto.UnitRequestDto;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

import java.util.UUID;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Page<Unit> getUnits(Pageable pageable) {
        return unitRepository.findAll(pageable);
    }

    public Unit createUnit(UnitRequestDto unitRequestDto) throws BuildingNotFoundException, AccountNotFoundException {
        Account owner = accountRepository.findByEmailIgnoreCase(unitRequestDto.getOwner().getEmail()).orElseThrow(AccountNotFoundException::new);
        Building building = buildingRepository.findById(unitRequestDto.getBuilding().getId()).orElseThrow(BuildingNotFoundException::new);

        Unit unit = new Unit();
        unit.setOwner(owner);
        unit.setBuilding(building);
        unit.setName(unitRequestDto.getName());

        owner.addRole(Role.OWNER);
        accountRepository.save(owner);

        return unitRepository.save(unit);
    }

    public Unit getUnit(UUID id) throws UnitNotFoundException {
        return unitRepository.findById(id).orElseThrow(UnitNotFoundException::new);
    }

    public Unit setName(UUID id, ChangeNameRequestDto changeNameRequestDto) throws UnitNotFoundException {
        Unit unit = unitRepository.findById(id).orElseThrow(UnitNotFoundException::new);

        unit.setName(changeNameRequestDto.getName());

        return unitRepository.save(unit);
    }
}
