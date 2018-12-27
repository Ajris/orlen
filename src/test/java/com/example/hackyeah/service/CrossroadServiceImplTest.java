package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringJUnitConfig
@WebMvcTest(CrossroadService.class)
public class CrossroadServiceImplTest {

    @Autowired
    private CrossroadService crossroadService;

    @MockBean
    private CrossroadRepository crossroadRepository;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    void shouldDeleteWorks() {
        String id = "123";
        doNothing()
                .when(crossroadRepository)
                .deleteById(id);

        crossroadService.deleteById(id);

        verify(crossroadRepository, times(1))
                .deleteById(id);
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldFindAll() {
        Crossroad crossroad1 = Crossroad.builder()
                .id("1")
                .build();
        Crossroad crossroad2 = Crossroad.builder()
                .id("2")
                .build();
        when(crossroadRepository.findAll())
                .thenReturn(List.of(crossroad1, crossroad2));

        List<Crossroad> crossroads = crossroadService.findAll();

        Assertions.assertEquals(crossroads, List.of(crossroad1, crossroad2));

        verify(crossroadRepository, times(1))
                .findAll();
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldSave() {
        Crossroad crossroad1 = Crossroad.builder()
                .id("1")
                .build();
        when(crossroadRepository.save(crossroad1))
                .thenReturn(crossroad1);

        Crossroad returnedCrossroad = crossroadService.save(crossroad1);

        Assertions.assertEquals(returnedCrossroad, crossroad1);

        verify(crossroadRepository, times(1))
                .save(crossroad1);
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldFindById() {
        Crossroad crossroad1 = Crossroad.builder()
                .id("1")
                .build();

        String id = "1";
        when(crossroadRepository.findById(id))
                .thenReturn(Optional.of(crossroad1));

        Crossroad returnedCrossroad = crossroadService.findById(id);

        Assertions.assertEquals(returnedCrossroad, crossroad1);

        verify(crossroadRepository, times(1))
                .findById(id);
        verifyNoMoreInteractions(crossroadRepository);
    }
}