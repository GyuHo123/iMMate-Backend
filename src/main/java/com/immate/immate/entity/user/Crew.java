package com.immate.immate.entity.user;

import com.immate.immate.entity.CrewPortfolio;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "crew")
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crew_name")
    private String crewName;

    @Column(name = "crew_description", columnDefinition = "TEXT")
    private String crewDescription;

    @Column(name="crew_min_investment")
    private Integer crewMinInvestment;

    @Column(name = "investment_style")
    private String investmentStyle;

    @Column(name = "crew_total_investment", nullable = false)
    private Double crewTotalInvestment = 0.0;

    @Column(name = "crew_total_profit", nullable = false)
    private Double crewTotalProfit = 0.0;

    @Column(name = "crew_total_evaluation", nullable = false)
    private Double crewTotalEvaluation = 0.0;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CrewPortfolio> portfolios;

    //    총 투자 금액, 수익 금액, 평가 금액 계산
    public void calculateTotals() {
        this.crewTotalInvestment = portfolios.stream()
                .mapToDouble(CrewPortfolio::getInvestmentAmount)
                .sum();

        this.crewTotalProfit = portfolios.stream()
                .mapToDouble(CrewPortfolio::getProfitAmount)
                .sum();

        this.crewTotalEvaluation = portfolios.stream()
                .mapToDouble(CrewPortfolio::getEvaluationAmount)
                .sum();
    }

    @ManyToMany
    @JoinTable(
            name = "crew_user",
            joinColumns = @JoinColumn(name = "crew_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Column(name = "crew_yield")
    private double crewYield;

    @Builder
    public Crew(String crewName, String crewDescription, Integer crewMinInvestment) {
        this.crewName = crewName;
        this.crewDescription = crewDescription;
        this.crewMinInvestment = crewMinInvestment;
    }
}
