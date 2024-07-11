package es.minddata.challenge.service;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.entity.*;
import es.minddata.challenge.error.StarShipNotFoundException;
import es.minddata.challenge.repository.EngineRepository;
import es.minddata.challenge.repository.StarShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Slf4j
@RequiredArgsConstructor
public class StarShipServiceImpl implements StarShipService {


    private final StarShipRepository starShipRepository;

    private final EngineRepository engineRepository;

    private final StarShipFabric starShipFabric;

    @Transactional
    public StarShipDto createStarShip(StarShipDto starShipDTO) {
        StarShip starShip = createEntityFromDto(starShipDTO);
        return convertToDto(starShipRepository.save(starShip));
    }

    @Transactional(readOnly = true)
    public Page<StarShipDto> findAllStarShips(String text, String sort, int page, int size) {
        Sort sortingCriteria = Sort.by("id");
        if ("name".equals(sort)) {
            sortingCriteria = Sort.by("name");
        }

        Pageable pageable = PageRequest.of(page, size, sortingCriteria);
        Page<StarShip> starShipPage;

        if (text != null && !text.isBlank()) {
            starShipPage = starShipRepository.findAllByNameContaining(text, pageable);
        } else {
            starShipPage = starShipRepository.findAll(pageable);
        }

        return starShipPage.map(this::convertToDto);
    }

    @Transactional(readOnly = true)
    public StarShipDto findStarShipById(Long id) {
        return starShipRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Transactional
    public StarShipDto updateStarShip(Long id, StarShipDto starShipDto) {

        StarShip starShip = starShipRepository.findById(id)
                .orElseThrow(() -> new StarShipNotFoundException("Starship with id " + id + " not found"));

        StarShip createdStarShip = updateStarShipFromDto(starShip, starShipDto);

        return convertToDto(starShipRepository.save(createdStarShip));
    }

    private StarShip updateStarShipFromDto(StarShip existingShip, StarShipDto starShipDto) {
        existingShip.setName(starShipDto.getName());
        existingShip.setMaxSpeed(starShipDto.getMaxSpeed());
        existingShip.setWeight(starShipDto.getWeight());
        existingShip.setSize(starShipDto.getSize());
        existingShip.setShieldStrength(starShipDto.getShieldStrength());
        existingShip.setEngine(starShipDto.getEngine());
        if (existingShip instanceof CargoVessel) {
            CargoVessel cv = (CargoVessel) existingShip;
            cv.setMaxCapacity(starShipDto.getMaxCapacity());
            cv.setActualLoad(starShipDto.getActualLoad());
        }
        if (existingShip instanceof WarShip) {
            WarShip ws = (WarShip) existingShip;
            ws.setMaxGunPower(starShipDto.getMaxGunPower());
        }
        if (existingShip instanceof ArtifactShip) {
            ArtifactShip as = (ArtifactShip) existingShip;
            as.setMaxCapacity(starShipDto.getMaxCapacity());
            as.setActualLoad(starShipDto.getActualLoad());
            as.setMaxGunPower(starShipDto.getMaxGunPower());
        }
        return existingShip;
    }


    @Transactional
    public boolean deleteStarShip(Long id) {
        starShipRepository.deleteById(id);
        return starShipRepository.existsById(id);
    }

    private StarShipDto convertToDto(StarShip starShip) {
        StarShipDto dto = new StarShipDto();
        dto.setId(starShip.getId());
        dto.setName(starShip.getName());
        dto.setMaxSpeed(starShip.getMaxSpeed());
        dto.setWeight(starShip.getWeight());
        dto.setSize(starShip.getSize());
        dto.setShieldStrength(starShip.getShieldStrength());
        dto.setEngine(starShip.getEngine());

        if (starShip instanceof CargoVessel) {
            CargoVessel cv = (CargoVessel) starShip;
            dto.setMaxCapacity(cv.getMaxCapacity());
            dto.setActualLoad(cv.getActualLoad());
        }
        if (starShip instanceof WarShip) {
            WarShip ws = (WarShip) starShip;
            dto.setMaxGunPower(ws.getMaxGunPower());
        }
        if (starShip instanceof ArtifactShip) {
            ArtifactShip as = (ArtifactShip) starShip;
            dto.setMaxCapacity(as.getMaxCapacity());
            dto.setActualLoad(as.getActualLoad());
            dto.setMaxGunPower(as.getMaxGunPower());
        }
        return dto;
    }

    private StarShip createEntityFromDto(StarShipDto dto) {

        if (dto.getMaxCapacity() != null && dto.getMaxGunPower() != null) {

            ArtifactShip ship = starShipFabric.createArtifactShip(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxCapacity(),
                    dto.getActualLoad(),
                    dto.getMaxGunPower()
            );
            return ship;
        } else if (dto.getMaxCapacity() != null) {
            CargoVessel ship = starShipFabric.createCargoVessel(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxCapacity(),
                    dto.getActualLoad()
            );
            return ship;
        } else {
            WarShip ship = starShipFabric.createWarShip(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxGunPower()
            );
            return ship;
        }
    }
}
