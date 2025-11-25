package com.example.Parcial.service;

import com.example.Parcial.dto.StatsResponse;
import com.example.Parcial.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {
        long mutantCount = repository.countByIsMutant(true);
        long humanCount = repository.countByIsMutant(false);
        double ratio = humanCount == 0 ? 0.0 : (double) mutantCount / humanCount;
        return new StatsResponse(mutantCount, humanCount, ratio);
    }
}
