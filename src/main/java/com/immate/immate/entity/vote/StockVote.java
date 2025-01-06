package com.immate.immate.entity.vote;

import com.immate.immate.entity.investment.CrewInvestment;
import com.immate.immate.entity.investment.InvestmentStock;
import com.immate.immate.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StockVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private CrewInvestment plan;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private InvestmentStock stock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private VoteOption voteOption;

    private LocalDateTime votedAt;

    public enum VoteOption {
        BUY("매수"), HOLD("보류"), SELL("매도");

        private final String description;

        VoteOption(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
} 