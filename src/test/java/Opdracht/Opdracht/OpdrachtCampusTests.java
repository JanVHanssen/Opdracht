package Opdracht.Opdracht;

import Opdracht.Opdracht.controller.CampusController;
import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.service.CampusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.junit.Assert.assertNotNull;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampusController.class)
public class OpdrachtCampusTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampusService campusService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCampusTest() throws Exception {
        // Given
        Campus campus = new Campus();
        campus.setNaam("Test Campus");

        given(campusService.createCampus(any(Campus.class))).willReturn(campus);

        // When
        ResultActions response = mockMvc.perform(post("/campus/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")); // You can put JSON content here

        // Then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.naam", is(campus.getNaam())));
    }

    @Test
    public void getCampusByIdTest() throws Exception {
        // Given
        long campusId = 1;
        Campus campus = new Campus();
        campus.setNaam("Test Campus");

        given(campusService.getCampusById(campusId)).willReturn(campus);

        // When
        ResultActions response = mockMvc.perform(get("/campus/{id}", campusId));

        // Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.naam", is(campus.getNaam())));
    }
    @Test
    public void givenCampusList_whenGetAllCampus_thenReturnCampusList() throws Exception {
        List<Campus> campusList = new ArrayList<>();
        campusList.add(new Campus(1L, "Campus A", "Address A", 100L, 10L, new ArrayList<>()));
        campusList.add(new Campus(2L, "Campus B", "Address B", 200L, 20L, new ArrayList<>()));

        given(campusService.getAllCampus()).willReturn(campusList);

        mockMvc.perform(get("/campus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(campusList.size())));
    }





@Test
    public void givenUpdatedCampus_whenUpdateCampus_thenReturnUpdatedCampus() throws Exception {
        Long campusId = 1L;
        Campus updatedCampus = new Campus(campusId, "Updated Campus", "Updated Address", 300L, 30L, new ArrayList<>());

        assertNotNull(objectMapper); // Check if objectMapper is not null
        System.out.println("Updated Campus Object: " + updatedCampus); // Print the updatedCampus object

        given(campusService.updateCampus(updatedCampus)).willReturn(updatedCampus);

        String jsonContent = objectMapper.writeValueAsString(updatedCampus);
        System.out.println("JSON Content: " + jsonContent); // Print the JSON content being sent

        mockMvc.perform(put("/campus/{id}", campusId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCampusId_whenDeleteCampus_thenSuccessMessage() throws Exception {
        Long campusId = 1L;
        doNothing().when(campusService).deleteCampus(campusId);

        mockMvc.perform(delete("/campus/{id}", campusId))
                .andExpect(status().isOk())
                .andExpect(content().string("Campus successfully deleted!"));
    }

}






