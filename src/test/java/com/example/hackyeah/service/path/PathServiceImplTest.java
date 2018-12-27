package com.example.hackyeah.service.path;

import com.example.hackyeah.PathApplication;
import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import com.example.hackyeah.service.path.PathService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.stream.Collectors;

@SpringJUnitConfig
@ContextConfiguration(classes = PathApplication.class)
@TestPropertySource("/application.properties")
public class PathServiceImplTest {

    @Autowired
    private PathService pathService;

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Autowired
    private RoadRepository roadRepository;

    @BeforeEach
    void saveNecessaryRoadsAndCrossroads() {
        Road r1 = Road.builder().id("1")
                .start(Crossroad.builder().id("1").build())
                .end(Crossroad.builder().id("2").build())
                .build();

        Road r2 = Road.builder().id("2")
                .start(Crossroad.builder().id("2").build())
                .end(Crossroad.builder().id("3").build())
                .build();

        Road r3 = Road.builder().id("3")
                .start(Crossroad.builder().id("3").build())
                .end(Crossroad.builder().id("4").build())
                .build();

        Road r4 = Road.builder().id("4")
                .start(Crossroad.builder().id("2").build())
                .end(Crossroad.builder().id("5").build())
                .build();

        Road r5 = Road.builder().id("5")
                .start(Crossroad.builder().id("5").build())
                .end(Crossroad.builder().id("6").build())
                .build();

        Crossroad c1 = Crossroad.builder()
                .id("1")
                .connectedRoads(List.of(r1))
                .build();
        Crossroad c2 = Crossroad.builder()
                .id("2")
                .connectedRoads(List.of(r2, r4))
                .build();
        Crossroad c3 = Crossroad.builder()
                .id("3")
                .connectedRoads(List.of(r2,r3))
                .build();
        Crossroad c4 = Crossroad.builder()
                .id("4")
                .connectedRoads(List.of(r3))
                .build();
        Crossroad c5 = Crossroad.builder()
                .id("5")
                .connectedRoads(List.of(r4,r5))
                .build();
        Crossroad c6 = Crossroad.builder()
                .id("6")
                .connectedRoads(List.of(r5))
                .build();

        roadRepository.saveAll(List.of(r1,r2,r3,r4,r5));
        crossroadRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6));
    }

    @AfterEach
    public void clear(){
        roadRepository.deleteAll();
        crossroadRepository.deleteAll();
    }

    @Test
    void shouldFindPathFrom1To4() {
        PathFinderWrapper pathFinderWrapper = PathFinderWrapper.builder()
                .start(Crossroad.builder().id("1").build())
                .end(Crossroad.builder().id("4").build())
                .build();

        List<Crossroad> actual = pathService.findPath(pathFinderWrapper);
        List<String> ids = actual.stream()
                .map(Crossroad::getId)
                .collect(Collectors.toList());

        Assertions.assertEquals(ids, List.of("4","3","2","1"));
    }

    @Test
    void shouldFindPathFrom1To6() {
        PathFinderWrapper pathFinderWrapper = PathFinderWrapper.builder()
                .start(Crossroad.builder().id("1").build())
                .end(Crossroad.builder().id("6").build())
                .build();

        List<Crossroad> actual = pathService.findPath(pathFinderWrapper);
        List<String> ids = actual.stream()
                .map(Crossroad::getId)
                .collect(Collectors.toList());

        Assertions.assertEquals(ids, List.of("6","5","2","1"));
    }
}