package com.example.hackyeah.service;

import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@WebMvcTest(PathServiceImpl.class)
class PathServiceImplTest {

    @Autowired
    private PathService pathService;

    @MockBean
    private CrossroadRepository crossroadRepository;

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