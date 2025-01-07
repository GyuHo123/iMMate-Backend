package com.immate.immate.controller;

import com.immate.immate.dto.*;
import com.immate.immate.service.CrewDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crews")
public class CrewDetailController {
    private final CrewDetailService crewDetailService;

    public CrewDetailController(CrewDetailService crewDetailService) {
        this.crewDetailService = crewDetailService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCrew(@RequestBody CrewCreateRequest request) {
        crewDetailService.createCrew(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{crew_id}/details")
    public ResponseEntity<CrewDetailInfo> getCrewDetails(@PathVariable Long crew_id) {
        return ResponseEntity.ok(crewDetailService.getCrewDetails(crew_id));
    }

    @GetMapping("/{crewId}/vote-stocks")
    public ResponseEntity<List<VoteStockInfo>> getAllVoteStocks(@PathVariable Long crewId) {
        return ResponseEntity.ok(crewDetailService.getAllVoteStocks(crewId));
    }

    @PostMapping("/{crewId}/vote-stocks")
    public ResponseEntity<Void> createVoteStock(@PathVariable Long crewId, @RequestBody VoteStockCreateRequest request) {
        crewDetailService.createVoteStock(crewId, request);
        return ResponseEntity.ok().build();
    }
}
