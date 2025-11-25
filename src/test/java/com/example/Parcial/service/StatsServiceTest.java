package com.example.Parcial.service;

import com.example.Parcial.dto.StatsResponse;
import com.example.Parcial.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsServiceTest {

    @Mock
    private DnaRecordRepository repository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculatesRatioWhenHumanCountExists() {
        when(repository.countByIsMutant(true)).thenReturn(4L);
        when(repository.countByIsMutant(false)).thenReturn(2L);

        StatsResponse response = statsService.getStats();
        assertEquals(4L, response.getCount_mutant_dna());
        assertEquals(2L, response.getCount_human_dna());
        assertEquals(2.0, response.getRatio());
    }

    @Test
    void handlesZeroHumanCount() {
        when(repository.countByIsMutant(true)).thenReturn(1L);
        when(repository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse response = statsService.getStats();
        assertEquals(0.0, response.getRatio());
    }
}
