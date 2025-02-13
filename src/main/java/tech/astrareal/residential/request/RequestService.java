package tech.astrareal.residential.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.astrareal.residential.request.dto.DenyRequestRequestDto;
import tech.astrareal.residential.request.dto.FurnitureRequestDto;
import tech.astrareal.residential.request.dto.RequestRequestDto;
import tech.astrareal.residential.request.exceptions.RequestNotFoundException;
import tech.astrareal.residential.security.AuthenticationUtil;
import tech.astrareal.residential.unit.Unit;
import tech.astrareal.residential.unit.UnitRepository;
import tech.astrareal.residential.unit.exceptions.UnitNotFoundException;

import java.util.UUID;

@Service
public class RequestService {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private FurnitureRequestRepository furnitureRequestRepository;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    public Page<Request> getRequests(Pageable pageable) {
        return requestRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    private Request createBaseRequest(RequestRequestDto requestDto) throws UnitNotFoundException {
        Unit unit = unitRepository.findById(requestDto.getUnit().getId()).orElseThrow(UnitNotFoundException::new);

        Request request = new Request();
        request.setRequestedBy(authenticationUtil.getAccount());
        request.setUnit(unit);
        request.setType(requestDto.getType());

        return requestRepository.save(request);
    }

    @Transactional
    public FurnitureRequest createFurnitureRequest(FurnitureRequestDto furnitureRequestDto) throws UnitNotFoundException {
        Request request = createBaseRequest(furnitureRequestDto);

        FurnitureRequest furnitureRequest = new FurnitureRequest();
        furnitureRequest.setRequest(request);
        furnitureRequest.setMovingDate(furnitureRequestDto.getMovingDate());
        furnitureRequest.setMovingTimeFrom(furnitureRequestDto.getMovingTimeFrom());
        furnitureRequest.setMovingTimeTo(furnitureRequestDto.getMovingTimeTo());
        furnitureRequest.setRequirements(furnitureRequestDto.getRequirements());
        furnitureRequest.setOtherRequirements(furnitureRequestDto.getOtherRequirements());
        furnitureRequest.setNotes(furnitureRequestDto.getNotes());

        furnitureRequest.setItems(furnitureRequestDto.getItems().stream().map(dto -> {
            FurnitureItem item = new FurnitureItem();
            item.setRequest(furnitureRequest);
            item.setName(dto.getName());
            item.setQuantity(dto.getQuantity());
            item.setWidth(dto.getWidth());
            item.setHeight(dto.getHeight());
            item.setDepth(dto.getDepth());

            return item;
        }).toList());

        return furnitureRequestRepository.save(furnitureRequest);
    }

    public Request approveRequest(UUID id) throws RequestNotFoundException {
        Request request = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);

        request.setApprovedBy(authenticationUtil.getAccount());
        request.setDenied(false);
        request.setDenialReason(null);

        return requestRepository.save(request);
    }

    public Request denyRequest(UUID id, DenyRequestRequestDto denyRequestRequestDto) throws RequestNotFoundException {
        Request request = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);

        request.setApprovedBy(authenticationUtil.getAccount());
        request.setDenied(true);
        request.setDenialReason(denyRequestRequestDto.getReason());

        return requestRepository.save(request);
    }

    public Request executeRequest(UUID id) throws RequestNotFoundException {
        Request request = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);

        request.setExecutedBy(authenticationUtil.getAccount());

        return requestRepository.save(request);
    }

    public Request deleteRequest(UUID id) throws RequestNotFoundException {
        Request request = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);

        requestRepository.delete(request);

        return request;
    }
}
