package com.example.parcial.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        mutantDetector = new MutantDetector();
    }

    @Test
    void shouldDetectMutantWithHorizontalAndVerticalSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantWithDiagonalRightSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATGT",
                "AGAAGG",
                "TCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantWithDiagonalLeftSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTACTT",
                "AGCAGG",
                "GTCCCA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldReturnFalseForHumanDnaWithoutSequences() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldReturnFalseForHumanDnaWithSingleSequence() {
        String[] dna = {
                "AAAAGG",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "TCCCTA",
                "TCACTG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldReturnFalseForNullInput() {
        assertFalse(mutantDetector.isMutant(null));
    }

    @Test
    void shouldReturnFalseForEmptyInput() {
        assertFalse(mutantDetector.isMutant(new String[]{}));
    }

    @Test
    void shouldReturnFalseForNonSquareMatrix() {
        String[] dna = {
                "ATG",
                "CAGT",
                "TTAT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldReturnFalseForInvalidCharacters() {
        String[] dna = {
                "ATGX",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void shouldShortCircuitAfterTwoSequences() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
}
