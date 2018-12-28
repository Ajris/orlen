package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.service.crossroad.CrossroadService;
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
@WebMvcTest(CrossroadController.class)
public class CrossroadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CrossroadService crossroadServiceMock;

    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    public void shouldCreateNewCrossroad() throws Exception {
        Crossroad c1 = Crossroad.builder()
                .id("1")
                .latitude(1.1)
                .longitude(1.2)
                .build();

        when(crossroadServiceMock.addCrossroad(c1))
                .thenReturn(c1);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(c1);

        mvc.perform(MockMvcRequestBuilders.post("/crossroads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(crossroadServiceMock, times(1))
                .addCrossroad(c1);
        verifyNoMoreInteractions(crossroadServiceMock);
    }


    @Disabled
    @Test
    public void shouldUpdateCrossroad() throws Exception {
        Crossroad c1 = Crossroad.builder()
                .id("1")
                .latitude(1.1)
                .longitude(1.2)
                .build();

        when(crossroadServiceMock.addCrossroad(c1))
                .thenReturn(c1);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(c1);

        mvc.perform(MockMvcRequestBuilders.put("/crossroads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(crossroadServiceMock, times(1))
                .addCrossroad(c1);
        verifyNoMoreInteractions(crossroadServiceMock);
    }

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void shouldDeleteCrossroad() throws Exception {
        String id = "1";

        doNothing()
                .when(crossroadServiceMock)
                .deleteById(id);

        mvc.perform(MockMvcRequestBuilders.delete("/crossroads/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(crossroadServiceMock, times(1))
                .deleteById(id);
        verifyNoMoreInteractions(crossroadServiceMock);
    }

    @Test
    @WithMockUser("admin")
    public void shouldReturnListOfCrossroads() throws Exception {
        Crossroad c1 = Crossroad.builder()
                .id("1")
                .latitude(1.1)
                .longitude(1.2)
                .build();

        Crossroad c2 = Crossroad.builder()
                .id("2")
                .latitude(2.1)
                .longitude(2.2)
                .build();

        when(crossroadServiceMock.findAll())
                .thenReturn(Arrays.asList(c1, c2));

        mvc.perform(MockMvcRequestBuilders.get("/crossroads")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].latitude").value(1.1))
                .andExpect(jsonPath("$[0].longitude").value(1.2))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].latitude").value(2.1))
                .andExpect(jsonPath("$[1].longitude").value(2.2));

        verify(crossroadServiceMock, times(1))
                .findAll();
        verifyNoMoreInteractions(crossroadServiceMock);
    }
}
