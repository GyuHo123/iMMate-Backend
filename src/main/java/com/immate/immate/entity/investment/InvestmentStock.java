package com.immate.immate.entity.investment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvestmentStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private InvestmentPlan plan;

    private String stockCode;
    private String stockName;
    private Double currentPrice;
    private String description;
} 