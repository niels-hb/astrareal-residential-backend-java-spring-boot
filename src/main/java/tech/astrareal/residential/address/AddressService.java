package tech.astrareal.residential.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.address.dto.AddressRequestDto;
import tech.astrareal.residential.address.exceptions.AddressNotFoundException;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Page<Address> getAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address createAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setCity(addressRequestDto.getCity());
        address.setPostalCode(addressRequestDto.getPostalCode());
        address.setCountry(addressRequestDto.getCountry());

        return addressRepository.save(address);
    }

    public Address updateAddress(UUID id, AddressRequestDto addressRequestDto) throws AddressNotFoundException {
        Address address = addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);

        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setCity(addressRequestDto.getCity());
        address.setPostalCode(addressRequestDto.getPostalCode());
        address.setCountry(addressRequestDto.getCountry());

        return addressRepository.save(address);
    }
}
