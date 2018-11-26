package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import com.example.hackyeah.service.CrossroadService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CrossroadController.class)
public class CrossroadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CrossroadService crossroadServiceMock;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    public void getCrossroads() throws Exception {
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
