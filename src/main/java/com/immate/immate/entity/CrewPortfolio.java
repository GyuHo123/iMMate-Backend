package com.immate.immate.entity;

import com.immate.immate.entity.user.Crew;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "crew_portfolio")
public class CrewPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crew_id", nullable = false)
    private Crew crew;

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    @Column(name = "investment_amount", nullable = false)
    private Double investmentAmount;

    @Column(name = "profit_amount", nullable = false)
    private Double profitAmount;

    @Transient
    public Double getEvaluationAmount() {
        return investmentAmount + profitAmount;
    }

    @Column(name = "yield", nullable = false)
    private Double yield;
}
