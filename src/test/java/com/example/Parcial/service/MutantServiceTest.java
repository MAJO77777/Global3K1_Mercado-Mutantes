package com.example.Parcial.service;

import com.example.Parcial.entity.DnaRecord;
import com.example.Parcial.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MutantServiceTest {

    @Mock
    private DnaRecordRepository repository;
    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantService mutantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnsCachedResultWhenHashExists() {
        String[] dna = {"AAAA", "AAAA", "AAAA", "AAAA"};
        DnaRecord cached = new DnaRecord();
        cached.setDnaHash("hash");
        cached.setIsMutant(true);
        cached.setCreatedAt(LocalDateTime.now());

        when(repository.findByDnaHash(any())).thenReturn(Optional.of(cached));
        boolean result = mutantService.analyzeDna(dna);

        assertTrue(result);
        verify(mutantDetector, never()).isMutant(any());
        verify(repository, never()).save(any());
    }

    @Test
    void savesNewRecordWhenNotCached() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGAC"};
        when(repository.findByDnaHash(any())).thenReturn(Optional.empty());
        when(mutantDetector.isMutant(dna)).thenReturn(false);

        boolean result = mutantService.analyzeDna(dna);

        assertFalse(result);
        ArgumentCaptor<DnaRecord> captor = ArgumentCaptor.forClass(DnaRecord.class);
        verify(repository).save(captor.capture());
        assertEquals(false, captor.getValue().isMutant());
    }
}
