package com.immate.immate.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CrewPortfolioInfo {
    private String stockName;
    private Double investmentAmount;
    private Double profitAmount;
    private Double evaluationAmount;

    public CrewPortfolioInfo(String stockName, Double investmentAmount, Double profitAmount, Double evaluationAmount) {
        this.stockName = stockName;
        this.investmentAmount = investmentAmount;
        this.profitAmount = profitAmount;
        this.evaluationAmount = evaluationAmount;
    }
}
