package com.immate.immate.service;

import com.immate.immate.entity.investment.CrewInvestment;
import com.immate.immate.entity.investment.TradingPlan;
import com.immate.immate.exception.InvalidInvestmentStateException;
import com.immate.immate.repo.CrewInvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrewInvestmentService {
    private final CrewInvestmentRepository investmentRepository;
    private final TradingPlanService tradingPlanService;

    /**
     * 새로운 투자 계획을 생성하고 활성화
     */
    @Transactional
    public CrewInvestment createPlan(CrewInvestment plan) {
        plan.setActive(true);
        plan.setStartTime(LocalDateTime.now());
        plan.setStatus(CrewInvestment.InvestmentStatus.IN_PROGRESS);
        return investmentRepository.save(plan);
    }

    /**
     * 투자 계획의 투표를 종료하고 매매 계획으로 전환
     * 매수/매도가 최다 득표된 주식들에 대해서만 매매 계획 생성
     * HOLD가 최다 득표되거나 동률인 경우 매매 계획에서 제외
     */
    @Transactional
    public List<TradingPlan> finalizePlan(Long planId) {
        CrewInvestment plan = investmentRepository.findById(planId)
                .orElseThrow(() -> new InvalidInvestmentStateException("Invalid plan ID: " + planId));

        if (plan.getStatus() != CrewInvestment.InvestmentStatus.IN_PROGRESS) {
            throw new InvalidInvestmentStateException("Plan is not in progress: " + planId);
        }

        List<TradingPlan> tradingPlans = plan.getStocks().stream()
                .map(stock -> tradingPlanService.createTradingPlan(plan, stock))
                .filter(tradingPlan -> tradingPlan != null)
                .collect(Collectors.toList());

        plan.setStatus(CrewInvestment.InvestmentStatus.COMPLETED);
        plan.setEndTime(LocalDateTime.now());
        investmentRepository.save(plan);

        return tradingPlans;
    }
} 