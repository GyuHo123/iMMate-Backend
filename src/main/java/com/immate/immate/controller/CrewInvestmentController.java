package com.immate.immate.controller;

import com.immate.immate.entity.investment.CrewInvestment;
import com.immate.immate.entity.investment.TradingPlan;
import com.immate.immate.entity.vote.StockVote;
import com.immate.immate.service.CrewInvestmentService;
import com.immate.immate.service.StockVotingService;
import com.immate.immate.service.TradingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CrewInvestmentController {
    private final CrewInvestmentService investmentService;
    private final StockVotingService votingService;
    private final TradingPlanService tradingPlanService;

    /**
     * 크루의 새로운 투자 계획을 생성
     * WebSocket을 통해 실시간으로 생성 결과를 전송
     */
    @MessageMapping("/plans/create")
    public CrewInvestment createPlan(@Payload CrewInvestment plan) {
        return investmentService.createPlan(plan);
    }

    /**
     * 특정 주식에 대한 투표 진행 (BUY/HOLD/SELL)
     * WebSocket을 통해 실시간으로 투표 결과를 전송
     */
    @MessageMapping("/plans/{planId}/stocks/{stockId}/vote")
    public void vote(@Payload VoteRequest voteRequest) {
        votingService.vote(
            voteRequest.planId(),
            voteRequest.stockId(),
            voteRequest.userId(),
            voteRequest.voteOption()
        );
    }

    /**
     * 투자 계획의 투표를 종료하고 매매 계획으로 전환
     * 매수/매도가 최다 득표된 주식들에 대해 매매 계획 생성
     */
    @PostMapping("/api/plans/{planId}/finalize")
    public ResponseEntity<List<TradingPlan>> finalizePlan(@PathVariable Long planId) {
        return ResponseEntity.ok(investmentService.finalizePlan(planId));
    }

    /**
     * 매매 계획의 매매 비율 설정 (1~100%)
     * 매수/매도 최다 득표 주식에 대한 실제 매매 비율 결정
     */
    @PutMapping("/api/trading-plans/{tradingPlanId}/ratio")
    public ResponseEntity<TradingPlan> setTradingRatio(
            @PathVariable Long tradingPlanId,
            @RequestBody Double ratio) {
        return ResponseEntity.ok(tradingPlanService.setTradingRatio(tradingPlanId, ratio));
    }
}

record VoteRequest(
    Long planId,
    Long stockId,
    Long userId,
    StockVote.VoteOption voteOption
) {} 