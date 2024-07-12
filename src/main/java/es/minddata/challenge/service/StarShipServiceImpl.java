package es.minddata.challenge.service;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.entity.ArtifactShip;
import es.minddata.challenge.entity.CargoVessel;
import es.minddata.challenge.entity.StarShip;
import es.minddata.challenge.entity.WarShip;
import es.minddata.challenge.error.StarShipExistsException;
import es.minddata.challenge.error.StarShipNotFoundException;
import es.minddata.challenge.mapper.StarShipMapper;
import es.minddata.challenge.repository.EngineRepository;
import es.minddata.challenge.repository.StarShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StarShipServiceImpl implements StarShipService {


    private final StarShipRepository starShipRepository;

    private final EngineRepository engineRepository;


    @Transactional
    public StarShipDto createStarShip(StarShipDto starShipDTO) {

        validateName(starShipDTO.getName());

        engineRepository.save(starShipDTO.getEngine());

        StarShip starShip = StarShipMapper.createEntityFromDto(starShipDTO);

        StarShipDto createdStarShip = StarShipMapper.convertToDto(starShipRepository.save(starShip));

        log.debug("----- Starship with id {} created", createdStarShip.getId());

        return createdStarShip;
    }

    private void validateName(String name) {

        Optional<StarShip> starShipOptional = starShipRepository.findByName(name);
        if (starShipOptional.isPresent()) {
            throw new StarShipExistsException("La nave con este nombre ya existe");
        }
    }


    @Cacheable(value = "starships", key = "{#text, #sort, #page, #size}")
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

        return starShipPage.map(StarShipMapper::convertToDto);
    }

    @Cacheable(value = "starships", key = "#id")
    @Transactional(readOnly = true)
    public StarShipDto findStarShipById(Long id) {

        StarShip starShip = starShipRepository.findById(id)
                .orElseThrow(() -> new StarShipNotFoundException("Starship with id " + id + " not found"));

        log.debug("----- Starship with id {} found", id);

        return StarShipMapper.convertToDto(starShip);
    }

    @Transactional
    public StarShipDto updateStarShip(Long id, StarShipDto starShipDto) {

        StarShip starShip = starShipRepository.findById(id)
                .orElseThrow(() -> new StarShipNotFoundException("Starship with id " + id + " not found"));

        StarShip createdStarShip = updateStarShipFromDto(starShip, starShipDto);

        StarShip updatedStarShip = starShipRepository.save(createdStarShip);

        log.debug("----- Starship with id {} updated", id);

        return StarShipMapper.convertToDto(updatedStarShip);
    }

    @Transactional
    public boolean deleteStarShip(Long id) {
        starShipRepository.deleteById(id);
        return starShipRepository.existsById(id);
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

}
