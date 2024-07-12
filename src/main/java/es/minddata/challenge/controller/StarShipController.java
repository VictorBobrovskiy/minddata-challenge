package es.minddata.challenge.controller;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.service.StarShipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @ApiOperation(value = "Crear una nueva nave")
    public ResponseEntity<StarShipDto> createStarShip(@Valid @RequestBody StarShipDto starShipDto) {

        log.debug("----- New starship request with name {} received", starShipDto.getName());

        StarShipDto newStarShipDto = starShipService.createStarShip(starShipDto);

        return ResponseEntity.ok(newStarShipDto);
    }

    @GetMapping
    @ApiOperation(value = "Encontrar todas las naves paginable, sorteable y con busqueda por el texto")
    public ResponseEntity<Page<StarShipDto>> getAllStarShips(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        log.debug("----- Request for all starships received");

        Page<StarShipDto> starShips = starShipService.findAllStarShips(text, sort, from, size);

        return ResponseEntity.ok(starShips);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Encontrar la nave con su id")
    public ResponseEntity<StarShipDto> getStarShipById(@PathVariable Long id) {

        log.debug("----- Request for the starship with id {} received", id);

        StarShipDto starShipDto = starShipService.findStarShipById(id);

        return ResponseEntity.ok(starShipDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar la nave con su id")
    public ResponseEntity<StarShipDto> updateStarShip(@PathVariable Long id,
                                                      @Valid @RequestBody StarShipDto starShipDto
    ) {

        log.debug("----- Update request for  the starship with id {} received", id);

        StarShipDto updatedStarShipDto = starShipService.updateStarShip(id, starShipDto);

        return ResponseEntity.ok(updatedStarShipDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar la nave con su id")
    public ResponseEntity<?> deleteStarShip(@PathVariable Long id) {

        log.debug("----- Delete request for  the starship with id {} received", id);

        boolean deleted = starShipService.deleteStarShip(id);

        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}