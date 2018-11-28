package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.service.PathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PathController.class)
public class PathControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PathService pathService;

    @Test
    public void shouldSetPath() throws Exception {
        PathFinderWrapper pathFinderWrapper = PathFinderWrapper.builder()
                .build();

        List<Crossroad> crossroads = new ArrayList<>();

        when(pathService.getAll(pathFinderWrapper))
                .thenReturn(crossroads);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(pathFinderWrapper);

        mvc.perform(MockMvcRequestBuilders.put("/findPath")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pathService, times(1))
                .getAll(pathFinderWrapper);
        verifyNoMoreInteractions(pathService);
    }
}
