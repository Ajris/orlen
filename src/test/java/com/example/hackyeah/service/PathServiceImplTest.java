package com.example.hackyeah.service;

import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringJUnitConfig
@WebMvcTest(PathServiceImpl.class)
public class PathServiceImplTest {

    @Autowired
    private PathService pathService;

    @MockBean
    private CrossroadRepository crossroadRepository;

    @Disabled
    @Test
    void shouldFindPath() {
        PathFinderWrapper pathFinderWrapper = PathFinderWrapper.builder().build();

        when(crossroadRepository.findAll())
                .thenReturn(null);

        pathService.findPath(pathFinderWrapper);

        verify(crossroadRepository, times(1))
                .findAll();
        verifyNoMoreInteractions(crossroadRepository);
    }
}