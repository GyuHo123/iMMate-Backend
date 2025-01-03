package com.immate.immate.controller;

import com.immate.immate.dto.CrewInfo;
import com.immate.immate.service.CrewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crews")
public class CrewController {
    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/my-crews/{userId}")
    public ResponseEntity<List<CrewInfo>> getMyCrews(@PathVariable Long userId) {
        return ResponseEntity.ok(crewService.getMyCrews(userId));
    }

    @GetMapping("/recommended/{userId}")
    public ResponseEntity<List<CrewInfo>> getRecommendedCrews(@PathVariable Long userId) {
        return ResponseEntity.ok(crewService.getRecommendedCrews(userId));
    }

    @GetMapping("/top-ranking")
    public ResponseEntity<List<CrewInfo>> getTopRankingCrews() {
        return ResponseEntity.ok(crewService.getTopRankingCrews());
    }
} 