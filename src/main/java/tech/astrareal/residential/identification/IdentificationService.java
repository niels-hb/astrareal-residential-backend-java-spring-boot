package tech.astrareal.residential.identification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.identification.dto.IdentificationRequestDto;

@Service
public class IdentificationService {
    @Autowired
    private IdentificationRepository identificationRepository;

    public Page<Identification> getIdentifications(Pageable pageable) {
        return identificationRepository.findAll(pageable);
    }

    public Identification createIdentification(IdentificationRequestDto identificationRequestDto) {
        Identification identification = new Identification();
        identification.setType(identificationRequestDto.getType());
        identification.setFullName(identificationRequestDto.getFullName());
        identification.setIdNumber(identificationRequestDto.getIdNumber());

        return identificationRepository.save(identification);
    }
}
