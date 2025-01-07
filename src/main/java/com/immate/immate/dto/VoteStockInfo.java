package com.immate.immate.dto;

import com.immate.immate.entity.vote.VoteStock;

import lombok.Data;

@Data
public class VoteStockInfo {
    private String title;
    private String stockName;
    private String reason;

    public VoteStockInfo(String title, String stockName, String reason) {
        this.title = title;
        this.stockName = stockName;
        this.reason = reason;
    }
}
