package com.example.parcial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.parcial.entity.DnaRecord;
import com.example.parcial.repository.DnaRecordRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private DnaRecordRepository repository;

    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantService mutantService;

    private String[] dna;

    @BeforeEach
    void setUp() {
        dna = new String[]{"ATGC", "CAGT", "TTAT", "AGAC"};
    }

    @Test
    void shouldReturnCachedResultWhenHashExists() {
        DnaRecord record = new DnaRecord();
        record.setDnaHash("hash");
        record.setIsMutant(true);
        when(repository.findByDnaHash(anyString())).thenReturn(Optional.of(record));

        boolean result = mutantService.analyzeDna(dna);

        assertTrue(result);
        verify(repository).findByDnaHash(anyString());
    }

    @Test
    void shouldAnalyzeAndPersistWhenHashNotPresent() {
        when(repository.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(mutantDetector.isMutant(dna)).thenReturn(false);

        boolean result = mutantService.analyzeDna(dna);

        assertEquals(false, result);
        verify(mutantDetector).isMutant(dna);
        verify(repository).save(any(DnaRecord.class));
    }
}
