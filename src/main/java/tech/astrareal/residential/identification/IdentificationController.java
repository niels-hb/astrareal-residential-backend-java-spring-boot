package tech.astrareal.residential.identification;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.account.exceptions.AccountNotFoundException;
import tech.astrareal.residential.identification.dto.IdentificationMapper;
import tech.astrareal.residential.identification.dto.IdentificationRequestDto;
import tech.astrareal.residential.identification.dto.IdentificationResponseDto;

@RestController
@RequestMapping("/identifications")
public class IdentificationController {
    @Autowired
    private IdentificationService identificationService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<IdentificationResponseDto>> getIdentifications() {
        Page<Identification> identification = identificationService.getIdentifications(Pageable.unpaged());

        return ResponseEntity.ok(identification.map(IdentificationMapper.INSTANCE::identificationToIdentificationDto));
    }

    @PostMapping
    public ResponseEntity<IdentificationResponseDto> createIdentification(@RequestBody @Valid IdentificationRequestDto identificationRequestDto)
            throws AccountNotFoundException {
        Identification identification = identificationService.createIdentification(identificationRequestDto);

        return ResponseEntity.ok(IdentificationMapper.INSTANCE.identificationToIdentificationDto(identification));
    }
}
