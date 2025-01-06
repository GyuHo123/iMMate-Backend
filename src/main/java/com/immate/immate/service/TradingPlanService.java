package com.immate.immate.service;

import com.immate.immate.entity.investment.CrewInvestment;
import com.immate.immate.entity.investment.InvestmentStock;
import com.immate.immate.entity.investment.TradingPlan;
import com.immate.immate.entity.vote.StockVote;
import com.immate.immate.exception.InvalidTradingPlanException;
import com.immate.immate.repo.TradingPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TradingPlanService {
    private final TradingPlanRepository tradingPlanRepository;
    private final StockVotingService stockVotingService;

    /**
     * 매매 계획의 매매 비율을 설정 (1~100%)
     * 비율이 설정되면 매매 계획의 상태를 RATIO_SET으로 변경
     */
    @Transactional
    public TradingPlan setTradingRatio(Long tradingPlanId, Double ratio) {
        if (ratio < 1 || ratio > 100) {
            throw new InvalidTradingPlanException("Trading ratio must be between 1 and 100");
        }

        TradingPlan tradingPlan = tradingPlanRepository.findById(tradingPlanId)
                .orElseThrow(() -> new InvalidTradingPlanException("Invalid trading plan ID: " + tradingPlanId));

        if (tradingPlan.getStatus() != TradingPlan.PlanStatus.PENDING_RATIO) {
            throw new InvalidTradingPlanException("Trading plan is not in pending ratio status: " + tradingPlanId);
        }

        tradingPlan.setTradingRatio(ratio);
        tradingPlan.setRatioSetAt(LocalDateTime.now());
        tradingPlan.setStatus(TradingPlan.PlanStatus.RATIO_SET);

        return tradingPlanRepository.save(tradingPlan);
    }

    /**
     * 투자 계획과 주식 정보를 기반으로 매매 계획 생성
     */
    public TradingPlan createTradingPlan(CrewInvestment plan, InvestmentStock stock) {
        var voteResults = stockVotingService.getVoteResults(plan.getId(), stock.getId());
        StockVote.VoteOption winningOption = stockVotingService.determineWinningOption(voteResults);

        if (winningOption == StockVote.VoteOption.HOLD) {
            return null;
        }

        TradingPlan tradingPlan = new TradingPlan();
        tradingPlan.setPlan(plan);
        tradingPlan.setStock(stock);
        tradingPlan.setDecision(winningOption);
        tradingPlan.setDecidedAt(LocalDateTime.now());
        tradingPlan.setVoteResult(stockVotingService.createVoteResultString(voteResults));
        tradingPlan.setStatus(TradingPlan.PlanStatus.PENDING_RATIO);

        return tradingPlanRepository.save(tradingPlan);
    }
} 