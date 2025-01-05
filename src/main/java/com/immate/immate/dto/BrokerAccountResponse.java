package com.immate.immate.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BrokerAccountResponse {
    private String brokerName;
    private Long investmentAmount;
    private Long evaluationAmount;
    private List<StockResponse> stocks;
} 