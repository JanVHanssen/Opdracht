package Opdracht.Opdracht;

import Opdracht.Opdracht.controller.ReservatieController;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.service.LokaalService;
import Opdracht.Opdracht.service.ReservatieService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

@WebMvcTest(ReservatieController.class)
public class OpdrachtReservatieTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservatieService reservatieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createReservatieTest() throws Exception {
        // Given
        Reservatie reservatie = new Reservatie();
        LocalDateTime now = LocalDateTime.now();
        reservatie.setStart(now);

        given(reservatieService.createReservatie(any(Reservatie.class))).willReturn(reservatie);

        // When
        ResultActions response = mockMvc.perform(post("/reservatie/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservatie)));

        // Then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.start", is(now.format(DateTimeFormatter.ISO_DATE_TIME))));
    }
}