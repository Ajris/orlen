package com.example.hackyeah.service;

import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@WebMvcTest(RoadServiceImpl.class)
class RoadServiceImplTest {

    @Autowired
    private RoadService roadService;

    @MockBean
    private CrossroadRepository crossroadRepository;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    void shouldFindALlRoads() {
    }

    @Test
    void shouldSaveRoad() {
    }

    @Test
    void shouldFindRoadById() {
    }

    @Test
    void shouldDeleteRoadById() {
        String id = "1";
        doNothing()
                .when(roadRepository)
                .deleteById(id);

        roadService.deleteById(id);

        verify(roadRepository, times(1))
                .deleteById(id);
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldUpdateCrossroads() {
    }
}