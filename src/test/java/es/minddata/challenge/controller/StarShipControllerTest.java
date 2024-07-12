package es.minddata.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.entity.Engine;
import es.minddata.challenge.entity.StarShip;
import es.minddata.challenge.entity.WarShip;
import es.minddata.challenge.error.StarShipNotFoundException;
import es.minddata.challenge.mapper.StarShipMapper;
import es.minddata.challenge.service.StarShipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StarShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StarShipServiceImpl starShipService;

    private ObjectMapper objectMapper;

    private StarShipDto starShipDto;

    private Engine engine;

    private StarShip starShip;

    private String testJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        engine = new Engine(1L, "Ion Drive", true, 1000);

        starShipDto = new StarShipDto(
                1L,
                "New Ship",
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
        objectMapper = new ObjectMapper();
        testJson = objectMapper.writeValueAsString(starShip);
    }

    @Test
    public void testCreateStarShip() throws Exception {
        given(starShipService.createStarShip(any(StarShipDto.class))).willReturn(starShipDto);

        mockMvc.perform(post("/api/starships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("New Ship")))
                .andExpect(jsonPath("$.maxCapacity", equalTo(500)));
    }


    @Test
    public void getStarShipById_whenStarShipExists_shouldReturnStarShip() throws Exception {
        given(starShipService.findStarShipById(1L)).willReturn(starShipDto);

        mockMvc.perform(get("/api/starships/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("New Ship")))
                .andExpect(jsonPath("$.maxCapacity", equalTo(500)));
    }

    @Test
    public void testGetAllStarShips_DefaultPaginationAndSorting() throws Exception {
        Page<StarShipDto> starShipPage = new PageImpl<>(Collections.singletonList(starShipDto));

        given(starShipService.findAllStarShips(null, null, 0, 10)).willReturn(starShipPage);

        mockMvc.perform(get("/api/starships")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is("New Ship")));
    }

    @Test
    public void testGetAllStarShips_CustomPaginationAndSorting() throws Exception {
        Page<StarShipDto> starShipPage = new PageImpl<>(Collections.singletonList(starShipDto));

        given(starShipService.findAllStarShips(null, "name,desc", 1, 5)).willReturn(starShipPage);

        mockMvc.perform(get("/api/starships")
                        .param("sort", "name,desc")
                        .param("from", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is("New Ship")));
    }

    @Test
    public void testGetAllStarShips_WithSearchText() throws Exception {
        Page<StarShipDto> starShipPage = new PageImpl<>(Collections.singletonList(starShipDto));

        given(starShipService.findAllStarShips("New", null, 0, 10)).willReturn(starShipPage);

        mockMvc.perform(get("/api/starships")
                        .param("text", "New")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is("New Ship")));
    }


    @Test
    public void getStarShipById_whenStarShipDoesNotExist_shouldReturnNotFound() throws Exception {

        given(starShipService.findStarShipById(1L)).willThrow(StarShipNotFoundException.class);

        mockMvc.perform(get("/api/starships/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateStarShip_whenStarShipExists_shouldReturnUpdatedStarShip() throws Exception {

        starShipDto.setName("Updated Interceptor");

        String updatedStarShipJson = objectMapper.writeValueAsString(starShip);

        given(starShipService.findStarShipById(1L)).willReturn(starShipDto);
        given(starShipService.updateStarShip(eq(1L), (StarShipDto) any(StarShipDto.class))).willReturn(starShipDto);

        mockMvc.perform(put("/api/starships/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedStarShipJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Interceptor")));
    }

    @Test
    public void deleteStarShip_whenStarShipExists_shouldReturnStatusOk() throws Exception {
        given(starShipService.deleteStarShip(1L)).willReturn(true);

        mockMvc.perform(delete("/api/starships/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStarShip_whenStarShipDoesNotExist_shouldReturnNotFound() throws Exception {
        given(starShipService.deleteStarShip(1L)).willReturn(false);

        mockMvc.perform(delete("/api/starships/{id}", 1))
                .andExpect(status().isNotFound());
    }
}
