package com.immate.immate.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfitResponse {
    private final double totalProfit;
} 