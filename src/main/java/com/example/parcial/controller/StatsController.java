package com.example.parcial.controller;

import com.example.parcial.dto.StatsResponse;
import com.example.parcial.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Estadísticas", description = "API para obtener estadísticas de verificaciones de ADN")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    @Operation(summary = "Obtener estadísticas de ADN analizados")
    public ResponseEntity<StatsResponse> getStats() {
        StatsResponse stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
