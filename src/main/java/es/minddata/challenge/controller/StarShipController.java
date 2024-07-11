package es.minddata.challenge.controller;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.service.StarShipService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/starships")
@Validated
@RequiredArgsConstructor
@Slf4j
@Api("API para operaciones CRUD con naves")
public class StarShipController {

    private final StarShipService starShipService;


    @PostMapping
    public ResponseEntity<StarShipDto> createStarShip(@Valid @RequestBody StarShipDto starShipDTO) {
        StarShipDto newStarShipDto = starShipService.createStarShip(starShipDTO);
        return ResponseEntity.ok(newStarShipDto);
    }

    @GetMapping
    public ResponseEntity<Page<StarShipDto>> getAllStarShips(
                                             @RequestParam(required = false) String text,
                                             @RequestParam(required = false) String sort,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                             @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        Page<StarShipDto> starShips = starShipService.findAllStarShips(text, sort, from, size);
        return ResponseEntity.ok(starShips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarShipDto> getStarShipById(@PathVariable Long id) {
        StarShipDto starShipDTO = starShipService.findStarShipById(id);
        return starShipDTO != null ? ResponseEntity.ok(starShipDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarShipDto> updateStarShip(@PathVariable Long id,
                                                      @Valid @RequestBody StarShipDto starShipDTO) {
        StarShipDto updatedStarShipDto = starShipService.updateStarShip(id, starShipDTO);
        return ResponseEntity.ok(updatedStarShipDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStarShip(@PathVariable Long id) {
        boolean deleted = starShipService.deleteStarShip(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}