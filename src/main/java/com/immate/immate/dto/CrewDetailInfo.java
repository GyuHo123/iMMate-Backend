package com.immate.immate.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class CrewDetailInfo {
    private String crewName;
    private String crewDescription;
    private Integer crewMinInvestment;
    private Double totalInvestment;
    private Double totalProfit;
    private Double totalEvaluation;
    private List<CrewPortfolioInfo> portfolios;

    public CrewDetailInfo(String crewName, String crewDescription, Integer crewMinInvestment, Double totalInvestment, Double totalProfit, Double totalEvaluation, List<CrewPortfolioInfo> portfolios) {
        this.crewName = crewName;
        this.crewDescription = crewDescription;
        this.crewMinInvestment = crewMinInvestment;
        this.totalInvestment = totalInvestment;
        this.totalProfit = totalProfit;
        this.totalEvaluation = totalEvaluation;
        this.portfolios = portfolios;
    }
}
