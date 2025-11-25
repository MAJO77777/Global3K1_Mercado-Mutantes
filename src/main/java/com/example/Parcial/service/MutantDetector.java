package com.example.Parcial.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MutantDetector {
    private static final int SEQUENCE_LENGTH = 4;
    private static final Set<Character> VALID_BASES = Set.of('A', 'T', 'C', 'G');

    public boolean isMutant(String[] dna) {
        if (!isValidDna(dna)) {
            return false;
        }

        int n = dna.length;
        char[][] matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int sequenceCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (col <= n - SEQUENCE_LENGTH && checkHorizontal(matrix, row, col)) {
                    if (++sequenceCount > 1) {
                        return true;
                    }
                }
                if (row <= n - SEQUENCE_LENGTH && checkVertical(matrix, row, col)) {
                    if (++sequenceCount > 1) {
                        return true;
                    }
                }
                if (row <= n - SEQUENCE_LENGTH && col <= n - SEQUENCE_LENGTH && checkDiagonalRight(matrix, row, col)) {
                    if (++sequenceCount > 1) {
                        return true;
                    }
                }
                if (row <= n - SEQUENCE_LENGTH && col >= SEQUENCE_LENGTH - 1 && checkDiagonalLeft(matrix, row, col)) {
                    if (++sequenceCount > 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidDna(String[] dna) {
        if (dna == null || dna.length == 0) {
            return false;
        }

        int n = dna.length;
        for (String row : dna) {
            if (row == null || row.length() != n) {
                return false;
            }
            for (char c : row.toCharArray()) {
                if (!VALID_BASES.contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkHorizontal(char[][] matrix, int row, int col) {
        final char base = matrix[row][col];
        return matrix[row][col + 1] == base &&
                matrix[row][col + 2] == base &&
                matrix[row][col + 3] == base;
    }

    private boolean checkVertical(char[][] matrix, int row, int col) {
        final char base = matrix[row][col];
        return matrix[row + 1][col] == base &&
                matrix[row + 2][col] == base &&
                matrix[row + 3][col] == base;
    }

    private boolean checkDiagonalRight(char[][] matrix, int row, int col) {
        final char base = matrix[row][col];
        return matrix[row + 1][col + 1] == base &&
                matrix[row + 2][col + 2] == base &&
                matrix[row + 3][col + 3] == base;
    }

    private boolean checkDiagonalLeft(char[][] matrix, int row, int col) {
        final char base = matrix[row][col];
        return matrix[row + 1][col - 1] == base &&
                matrix[row + 2][col - 2] == base &&
                matrix[row + 3][col - 3] == base;
    }
}
