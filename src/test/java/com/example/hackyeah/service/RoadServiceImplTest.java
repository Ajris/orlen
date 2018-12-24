package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringJUnitConfig
@WebMvcTest(RoadServiceImpl.class)
public class RoadServiceImplTest {

    @Autowired
    private RoadService roadService;

    @MockBean
    private CrossroadRepository crossroadRepository;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    void shouldFindALlRoads() {
        Road r1 = Road.builder()
                .id("1")
                .build();
        Road r2 = Road.builder()
                .id("2")
                .build();
        when(roadRepository.findAll())
                .thenReturn(List.of(r1, r2));

        List<Road> actualRoads = roadService.findAll();

        Assertions.assertEquals(actualRoads, List.of(r1, r2));

        verify(roadRepository, times(1))
                .findAll();

        verifyNoMoreInteractions(roadRepository);
    }

    @Test
    void shouldSaveRoad() {
        Road expected = Road.builder()
                .id("1")
                .build();
        when(roadRepository.save(expected))
                .thenReturn(expected);

        Road actual = roadService.save(expected);

        Assertions.assertEquals(expected, actual);

        verify(roadRepository, times(1))
                .save(expected);
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldGetExceptionBecauseNoRoadWasFound() {
        String id = "1";
        when(roadRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NullPointerException.class, () -> roadService.findById(id));

        verify(roadRepository, times(1))
                .findById(id);
        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldFindRoadById() {
        String id = "1";
        Road expected = Road.builder()
                .id("1")
                .build();
        when(roadRepository.findById(id))
                .thenReturn(Optional.of(expected));

        Road actual = roadService.findById(id);

        Assertions.assertEquals(expected, actual);

        verify(roadRepository, times(1))
                .findById(id);
        verifyNoMoreInteractions(crossroadRepository);
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
    void shouldUpdateCrossroadIfTheyDontHaveAnyRoad() {
        Crossroad c1 = Crossroad.builder().id("1").latitude(1d).longitude(1d).build();
        Crossroad c2 = Crossroad.builder().id("2").latitude(2d).longitude(2d).build();
        Road r1 = Road.builder().id("1").start(c1).end(c2).build();
        Crossroad expectedC1 = Crossroad.builder().id("1").latitude(1d).longitude(1d)
                .connectedRoads(List.of(r1)).build();
        Crossroad expectedC2 = Crossroad.builder().id("2").latitude(2d).longitude(2d)
                .connectedRoads(List.of(r1)).build();

        RoadAdderWrapper roadAdderWrapper = RoadAdderWrapper.builder()
                .r1(r1)
                .c1(c1)
                .c2(c2)
                .build();

        when(crossroadRepository.findById("1"))
                .thenReturn(Optional.of(c1));
        when(crossroadRepository.findById("2"))
                .thenReturn(Optional.of(c2));

        roadService.updateCrossroads(roadAdderWrapper);


        verify(crossroadRepository, times(1))
                .findById("1");
        verify(crossroadRepository, times(1))
                .findById("2");
        verify(crossroadRepository, times(1))
                .save(expectedC1);
        verify(crossroadRepository, times(1))
                .save(expectedC2);

        verifyNoMoreInteractions(crossroadRepository);
    }

    @Test
    void shouldUpdateCrossroadsHavingRoad() {
        Crossroad c1 = Crossroad.builder().id("1").latitude(1d).longitude(1d).build();
        Road r1 = Road.builder().id("1").build();
        List<Road> roads = new ArrayList<>();
        roads.add(r1);

        Crossroad c2 = Crossroad.builder().id("2").latitude(2d).longitude(2d)
                .connectedRoads(roads).build();
        Crossroad expectedC1 = Crossroad.builder().id("1").latitude(1d).longitude(1d)
                .connectedRoads(List.of(r1)).build();
        Crossroad expectedC2 = Crossroad.builder().id("2").latitude(2d).longitude(2d)
                .connectedRoads(List.of(r1, r1)).build();

        RoadAdderWrapper roadAdderWrapper = RoadAdderWrapper.builder()
                .r1(r1)
                .c1(c1)
                .c2(c2)
                .build();

        when(crossroadRepository.findById("1"))
                .thenReturn(Optional.of(c1));
        when(crossroadRepository.findById("2"))
                .thenReturn(Optional.of(c2));

        roadService.updateCrossroads(roadAdderWrapper);


        verify(crossroadRepository, times(1))
                .findById("1");
        verify(crossroadRepository, times(1))
                .findById("2");
        verify(crossroadRepository, times(1))
                .save(expectedC1);
        verify(crossroadRepository, times(1))
                .save(expectedC2);

        verifyNoMoreInteractions(crossroadRepository);
    }
}