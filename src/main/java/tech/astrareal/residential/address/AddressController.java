package tech.astrareal.residential.address;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.astrareal.residential.address.dto.AddressMapper;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.address.dto.AddressResponseDto;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<Page<AddressResponseDto>> getAddresses() {
        Page<Address> addresses = addressService.getAddresses(Pageable.unpaged());

        return ResponseEntity.ok(addresses.map(AddressMapper.INSTANCE::addressToAddressDto));
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody @Valid AddressRequestDto addressRequestDto) {
        Address address = addressService.createAddress(addressRequestDto);

        return ResponseEntity.ok(AddressMapper.INSTANCE.addressToAddressDto(address));
    }
}
