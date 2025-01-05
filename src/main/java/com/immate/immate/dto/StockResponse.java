package com.immate.immate.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockResponse {
    private String stockName;
    private Long investmentAmount;
    private Long evaluationAmount;
    private Long quantity;
} 