package es.minddata.challenge.service;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.entity.Engine;
import es.minddata.challenge.entity.StarShip;
import es.minddata.challenge.error.StarShipNotFoundException;
import es.minddata.challenge.mapper.StarShipMapper;
import es.minddata.challenge.repository.EngineRepository;
import es.minddata.challenge.repository.StarShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class StarShipServiceImplTest {

    @Mock
    private StarShipRepository starShipRepository;

    @Mock
    private EngineRepository engineRepository;

    @InjectMocks
    private StarShipServiceImpl starShipService;

    private StarShipDto starShipDto;

    private Engine engine;

    private StarShip starShip;


    @BeforeEach
    void setUp() {
        engine = new Engine(1L, "Ion Drive", true, 1000);

        starShipDto = new StarShipDto(
                1L,
                "Test",
                2,
                5,
                10,
                400,
                engine,
                500,
                100,
                1000);

        starShip = StarShipMapper.createEntityFromDto(starShipDto);
        starShip.setId(starShipDto.getId());

    }

    @Test
    void testCreateStarShip() {
        when(starShipRepository.save(any(StarShip.class))).thenReturn(starShip);
        when(engineRepository.save(any(Engine.class))).thenReturn(engine);
        StarShipDto created = starShipService.createStarShip(starShipDto);
        assertNotNull(created);
        assertEquals(starShipDto, created);
    }

    @Test
    void testFindAllStarShips() {
        PageImpl<StarShip> page = new PageImpl<>(Arrays.asList(StarShipMapper.createEntityFromDto(starShipDto)));
        when(starShipRepository.findAll(any(Pageable.class))).thenReturn(page);
        Page<StarShipDto> starShips = starShipService.findAllStarShips(null, null, 0, 10);
        assertEquals(1, starShips.getTotalElements());
        assertEquals(starShipDto.getName(), starShips.getContent().get(0).getName());
    }

    @Test
    void testUpdateStarShipPartially() {
        when(starShipRepository.findById(anyLong())).thenReturn(Optional.of(starShip));
        when(starShipRepository.save(any(StarShip.class))).thenReturn(starShip);
        when(engineRepository.save(any(Engine.class))).thenReturn(engine);
        String updatedName = "Updated Name";
        starShipDto.setName(updatedName);
        StarShipDto updated = starShipService.updateStarShip(starShip.getId(), starShipDto);
        assertNotNull(updated);
        assertEquals(updatedName, updated.getName());
    }

    @Test
    void testFindStarShipById_Exists() {
        when(starShipRepository.findById(anyLong())).thenReturn(Optional.of(starShip));
        StarShipDto found = starShipService.findStarShipById(starShip.getId());
        assertNotNull(found);
        assertEquals(starShip.getName(), found.getName());
    }

    @Test
    void testFindStarShipById_NotFound() {
        when(starShipRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(StarShipNotFoundException.class, () -> {
            starShipService.findStarShipById(999L);  // Non-existing ID
        });
    }

    @Test
    void testDeleteStarShip_Success() {
        when(starShipRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(starShipRepository).deleteById(anyLong());
        boolean deleted = starShipService.deleteStarShip(starShip.getId());

        assertTrue(deleted);
    }

    @Test
    void testDeleteStarShip_Failure() {
        when(starShipRepository.existsById(anyLong())).thenReturn(false);
        boolean deleted = starShipService.deleteStarShip(999L);  // Non-existing ID
        assertFalse(deleted);
    }

    void testCreateStarShip_InvalidEngine() {
        when(engineRepository.findById(anyLong())).thenReturn(Optional.empty());  // No engine found for the ID
        assertThrows(Exception.class, () -> {
            starShipService.createStarShip(starShipDto);
        });
    }
}