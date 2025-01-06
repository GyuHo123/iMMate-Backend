package com.immate.immate.service;

import com.immate.immate.dto.*;
import com.immate.immate.entity.user.Crew;
import com.immate.immate.entity.CrewPortfolio;
import com.immate.immate.entity.vote.VoteStock;
import com.immate.immate.repo.CrewPortfolioRepository;
import com.immate.immate.repo.CrewRepository;
import com.immate.immate.repo.VoteStockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewDetailService {
    private final CrewRepository crewRepository;
    private final VoteStockRepository voteStockRepository;
    private final CrewPortfolioRepository crewPortfolioRepository;

    public CrewDetailService(CrewRepository crewRepository, VoteStockRepository voteStockRepository, CrewPortfolioRepository crewPortfolioRepository) {
        this.crewRepository = crewRepository;
        this.voteStockRepository = voteStockRepository;
        this.crewPortfolioRepository = crewPortfolioRepository;
    }

    // Crew 자체의 API
    @Transactional
    public void createCrew(CrewCreateRequest request) {
        Crew crew = Crew.builder()
                .crewName(request.getCrewName())
                .crewDescription(request.getCrewDescription())
                .crewMinInvestment(request.getCrewMinInvestment())
                .build();

        crewRepository.save(crew);
    }

    @Transactional(readOnly = true)
    public CrewDetailInfo getCrewDetails(Long crewId) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new RuntimeException("Crew not found with ID: " + crewId));

        crew.calculateTotals();

        List<CrewPortfolioInfo> portfolioInfos = crew.getPortfolios().stream()
                .map(portfolio -> new CrewPortfolioInfo(
                        portfolio.getStockName(),
                        portfolio.getInvestmentAmount(),
                        portfolio.getProfitAmount(),
                        portfolio.getEvaluationAmount()
                ))
                .collect(Collectors.toList());

        return convertToCrewDetailInfo(crew, portfolioInfos);
    }

    @Transactional(readOnly = true)
    public List<VoteStockInfo> getAllVoteStocks(Long crewId) {
        return voteStockRepository.findByCrewId(crewId).stream()
                .map(voteStock -> new VoteStockInfo(
                        voteStock.getTitle(),
                        voteStock.getStockName(),
                        voteStock.getReason()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createVoteStock(Long crewId, VoteStockCreateRequest request) {
        Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new RuntimeException("Crew not found"));
        CrewPortfolio portfolio = crewPortfolioRepository.findById(request.getPortfolioId()).orElseThrow(() -> new RuntimeException("Portfolio not found"));

        VoteStock voteStock = new VoteStock();
        voteStock.setCrew(crew);
        voteStock.setStockCode(request.getStockCode());
        voteStock.setStockName(request.getStockName());
        voteStock.setReason(request.getReason());
        voteStock.setTitle(request.getTitle());
        voteStockRepository.save(voteStock);
    }

    @Transactional
    public void finalizeVoteStock(Long crewId, Long voteStockId) {
        VoteStock voteStock = voteStockRepository.findById(voteStockId).orElseThrow(() -> new RuntimeException("VoteStock not found"));
        if (!voteStock.getCrew().getId().equals(crewId)) {
            throw new RuntimeException("VoteStock does not belong to the crew");
        }
        voteStockRepository.delete(voteStock);
    }

    private CrewDetailInfo convertToCrewDetailInfo(Crew crew, List<CrewPortfolioInfo> portfolioInfos) {
        return new CrewDetailInfo(
                crew.getCrewName(),
                crew.getCrewDescription(),
                crew.getCrewMinInvestment(),
                crew.getCrewTotalInvestment(),
                crew.getCrewTotalProfit(),
                crew.getCrewTotalEvaluation(),
                portfolioInfos
        );
    }
}
