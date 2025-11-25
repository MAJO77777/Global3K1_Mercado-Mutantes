package com.example.parcial.service;

import com.example.parcial.dto.StatsResponse;
import com.example.parcial.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {
        long mutants = repository.countByIsMutant(true);
        long humans = repository.countByIsMutant(false);
        double ratio = humans == 0 ? 0.0 : (double) mutants / humans;
        return new StatsResponse(mutants, humans, ratio);
    }
}
