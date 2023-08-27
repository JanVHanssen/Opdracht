package Opdracht.Opdracht;

import Opdracht.Opdracht.controller.LokaalController;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.service.LokaalService;
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

@WebMvcTest(LokaalController.class)
public class OpdrachtLokaalTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LokaalService lokaalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createLokaalTest() throws Exception {
        // Given
        Lokaal lokaal = new Lokaal();
        lokaal.setNaam("Test Lokaal");

        given(lokaalService.createLokaal(any(Lokaal.class))).willReturn(lokaal);

        // When
        ResultActions response = mockMvc.perform(post("/lokaal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")); // You can put JSON content here

        // Then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.naam", is(lokaal.getNaam())));
    }
}