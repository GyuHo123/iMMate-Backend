package com.immate.immate.entity.investment;

import com.immate.immate.entity.vote.StockVote;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TradingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private CrewInvestment plan;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private InvestmentStock stock;

    @Enumerated(EnumType.STRING)
    private StockVote.VoteOption decision;

    private LocalDateTime decidedAt;
    private LocalDateTime ratioSetAt;
    private Double tradingRatio;
    private String voteResult;

    @Enumerated(EnumType.STRING)
    private PlanStatus status = PlanStatus.PENDING_RATIO;

    public enum PlanStatus {
        PENDING_RATIO("비율 설정 대기"),
        RATIO_SET("비율 설정 완료"),
        EXECUTED("실행 완료"),
        CANCELLED("취소됨");

        private final String description;

        PlanStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
} 