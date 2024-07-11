package tech.astrareal.residential.request;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.request.dto.DenyRequestRequestDto;
import tech.astrareal.residential.request.dto.FurnitureRequestDto;
import tech.astrareal.residential.request.dto.RequestMapper;
import tech.astrareal.residential.request.dto.RequestResponseDto;
import tech.astrareal.residential.request.exceptions.RequestNotFoundException;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<RequestResponseDto>> getRequests() {
        return ResponseEntity.ok(requestService.getRequests(Pageable.unpaged()).map(RequestMapper.INSTANCE::requestToRequestResponseDto));
    }

    @PostMapping("furniture")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RequestResponseDto> createFurnitureRequest(@RequestBody @Valid FurnitureRequestDto furnitureRequestDto) throws UnitNotFoundException {
        FurnitureRequest furnitureRequest = requestService.createFurnitureRequest(furnitureRequestDto);

        return ResponseEntity.ok(RequestMapper.INSTANCE.furnitureRequestToFurnitureResponseDto(furnitureRequest));
    }

    @PutMapping("{id}/approve")
    @PreAuthorize("isAuthenticated() and hasRole('OWNER')")
    public ResponseEntity<RequestResponseDto> approveRequest(@PathVariable UUID id) throws RequestNotFoundException {
        Request request = requestService.approveRequest(id);

        return ResponseEntity.ok(RequestMapper.INSTANCE.requestToRequestResponseDto(request));
    }

    @PutMapping("{id}/deny")
    @PreAuthorize("isAuthenticated() and hasRole('OWNER')")
    public ResponseEntity<RequestResponseDto> denyRequest(@PathVariable UUID id, @RequestBody @Valid DenyRequestRequestDto denyRequestRequestDto) throws RequestNotFoundException {
        Request request = requestService.denyRequest(id, denyRequestRequestDto);

        return ResponseEntity.ok(RequestMapper.INSTANCE.requestToRequestResponseDto(request));
    }

    @PutMapping("{id}/execute")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<RequestResponseDto> executeRequest(@PathVariable UUID id) throws RequestNotFoundException {
        Request request = requestService.executeRequest(id);

        return ResponseEntity.ok(RequestMapper.INSTANCE.requestToRequestResponseDto(request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RequestResponseDto> deleteRequest(@PathVariable UUID id) throws RequestNotFoundException {
        Request request = requestService.deleteRequest(id);

        return ResponseEntity.ok(RequestMapper.INSTANCE.requestToRequestResponseDto(request));
    }
}
