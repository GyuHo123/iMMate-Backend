package com.immate.immate.entity.vote;

import com.immate.immate.entity.CrewPortfolio;
import com.immate.immate.entity.user.Crew;
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

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private CrewPortfolio portfolio;

    @Column(name = "title")
    private String title;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "target_price")
    private Double targetPrice;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;
} 