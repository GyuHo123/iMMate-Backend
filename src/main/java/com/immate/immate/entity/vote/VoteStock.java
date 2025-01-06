package com.immate.immate.entity.vote;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VoteStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_vote_id")
    private StockVote stockVote;

    private String stockCode;
    private String stockName;
    private Double targetPrice;
    private String reason; 
} 