package es.minddata.challenge.service;

import es.minddata.challenge.dto.StarShipDto;
import org.springframework.data.domain.Page;

public interface StarShipService {
    StarShipDto createStarShip(StarShipDto starShip);

    Page<StarShipDto> findAllStarShips(String text, String sort, int page, int size);

    StarShipDto findStarShipById(Long id);

    StarShipDto updateStarShip(Long id, StarShipDto starShipDetails);

    boolean deleteStarShip(Long id);
}
