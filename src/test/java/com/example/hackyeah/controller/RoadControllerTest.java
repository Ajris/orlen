package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadWrapper;
import com.example.hackyeah.service.road.RoadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(RoadController.class)
public class RoadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoadService roadServiceMock;


    @Disabled
    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    void shouldAddRoad() throws Exception {
        Road road = Road.builder()
                .id("1")
                .build();

        RoadWrapper c1 = RoadWrapper.builder()
                .road(road)
                .startingCrossroad(new Crossroad())
                .endingCrossroad(new Crossroad())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(c1);

        mvc.perform(MockMvcRequestBuilders.post("/roads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(roadServiceMock, times(1))
                .save(c1.getRoad());
        verify(roadServiceMock, times(1))
                .updateCrossroads(c1);
        verifyNoMoreInteractions(roadServiceMock);
    }

    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    void shouldSetRoad() throws Exception {
        Road road = Road.builder()
                .id("1")
                .build();

        when(roadServiceMock.save(road))
                .thenReturn(road);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(road);

        mvc.perform(MockMvcRequestBuilders.put("/roads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(roadServiceMock, times(1))
                .save(road);
        verifyNoMoreInteractions(roadServiceMock);
    }

    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    void shouldDeleteRoad() throws Exception {
        String id = "1";

        doNothing()
                .when(roadServiceMock)
                .deleteById(id);

        mvc.perform(MockMvcRequestBuilders.delete("/roads/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(roadServiceMock, times(1))
                .deleteById(id);
        verifyNoMoreInteractions(roadServiceMock);
    }

    @WithMockUser("admin")
    @Test
    void shouldReturnListOfRoads() throws Exception {
        Road c1 = Road.builder()
                .id("1")
                .build();

        Road c2 = Road.builder()
                .id("2")
                .build();

        when(roadServiceMock.findAll())
                .thenReturn(Arrays.asList(c1, c2));

        mvc.perform(MockMvcRequestBuilders.get("/roads")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));

        verify(roadServiceMock, times(1))
                .findAll();
        verifyNoMoreInteractions(roadServiceMock);
    }
}
