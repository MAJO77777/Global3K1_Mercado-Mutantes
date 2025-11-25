package com.example.parcial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.parcial.dto.StatsResponse;
import com.example.parcial.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private DnaRecordRepository repository;

    @InjectMocks
    private StatsService statsService;

    @Test
    void shouldReturnStatsWithRatio() {
        when(repository.countByIsMutant(true)).thenReturn(4L);
        when(repository.countByIsMutant(false)).thenReturn(2L);

        StatsResponse response = statsService.getStats();

        assertEquals(4L, response.getCount_mutant_dna());
        assertEquals(2L, response.getCount_human_dna());
        assertEquals(2.0, response.getRatio());
    }

    @Test
    void shouldHandleZeroHumans() {
        when(repository.countByIsMutant(true)).thenReturn(3L);
        when(repository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse response = statsService.getStats();

        assertEquals(0.0, response.getRatio());
    }
}
