package com.immate.immate.dto;

import lombok.Data;

@Data
public class VoteStockCreateRequest {
    private String title;
    private String stockName;
    private String stockCode;
    private String reason;
    private Long portfolioId;
}
