package com.immate.immate.service;

import com.immate.immate.entity.investment.CrewInvestment;
import com.immate.immate.entity.investment.InvestmentStock;
import com.immate.immate.entity.vote.StockVote;
import com.immate.immate.entity.user.User;
import com.immate.immate.exception.InvalidInvestmentStateException;
import com.immate.immate.exception.InvalidVoteException;
import com.immate.immate.repo.CrewInvestmentRepository;
import com.immate.immate.repo.StockVoteRepository;
import com.immate.immate.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockVotingService {
    private final CrewInvestmentRepository investmentRepository;
    private final StockVoteRepository voteRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 특정 주식에 대한 투표를 진행
     * 이미 투표한 경우 기존 투표를 수정
     * 투표 결과를 실시간으로 웹소켓을 통해 전송
     */
    @Transactional
    public StockVote vote(Long planId, Long stockId, Long userId, StockVote.VoteOption voteOption) {
        CrewInvestment plan = investmentRepository.findById(planId)
                .orElseThrow(() -> new InvalidInvestmentStateException("Invalid plan ID: " + planId));

        if (!plan.isActive() || plan.getStatus() != CrewInvestment.InvestmentStatus.IN_PROGRESS) {
            throw new InvalidInvestmentStateException("Plan is not active or already completed: " + planId);
        }

        StockVote vote = voteRepository.findByPlanIdAndStockIdAndUserId(planId, stockId, userId)
                .orElse(new StockVote());

        InvestmentStock stock = plan.getStocks().stream()
                .filter(s -> s.getId().equals(stockId))
                .findFirst()
                .orElseThrow(() -> new InvalidVoteException("Invalid stock ID: " + stockId));

        vote.setPlan(plan);
        vote.setStock(stock);
        vote.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new InvalidVoteException("Invalid user ID: " + userId)));
        vote.setVoteOption(voteOption);
        vote.setVotedAt(LocalDateTime.now());

        StockVote savedVote = voteRepository.save(vote);
        broadcastVoteResults(planId, stockId);
        
        return savedVote;
    }

    /**
     * 특정 주식의 투표 결과를 집계
     */
    public Map<StockVote.VoteOption, Long> getVoteResults(Long planId, Long stockId) {
        List<Object[]> results = voteRepository.countVotesByOptionAndStock(planId, stockId);
        Map<StockVote.VoteOption, Long> voteResults = new HashMap<>();
        
        for (StockVote.VoteOption option : StockVote.VoteOption.values()) {
            voteResults.put(option, 0L);
        }

        for (Object[] result : results) {
            StockVote.VoteOption option = (StockVote.VoteOption) result[0];
            Long count = ((Number) result[1]).longValue();
            voteResults.put(option, count);
        }

        return voteResults;
    }

    private void broadcastVoteResults(Long planId, Long stockId) {
        Map<StockVote.VoteOption, Long> results = getVoteResults(planId, stockId);
        messagingTemplate.convertAndSend(
            String.format("/topic/plans/%d/stocks/%d/votes", planId, stockId), 
            results
        );
    }

    public StockVote.VoteOption determineWinningOption(Map<StockVote.VoteOption, Long> voteResults) {
        return voteResults.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(StockVote.VoteOption.HOLD);
    }

    public String createVoteResultString(Map<StockVote.VoteOption, Long> voteResults) {
        return String.format(
                "BUY %d표, SELL %d표, HOLD %d표",
                voteResults.get(StockVote.VoteOption.BUY),
                voteResults.get(StockVote.VoteOption.SELL),
                voteResults.get(StockVote.VoteOption.HOLD)
        );
    }
} 