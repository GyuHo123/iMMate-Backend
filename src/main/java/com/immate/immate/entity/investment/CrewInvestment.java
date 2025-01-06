package com.immate.immate.entity.investment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CrewInvestment extends InvestmentPlan {
    @Enumerated(EnumType.STRING)
    private InvestmentStatus status = InvestmentStatus.IN_PROGRESS;

    public enum InvestmentStatus {
        IN_PROGRESS("진행중"),
        COMPLETED("완료됨"),
        CANCELLED("취소됨");

        private final String description;

        InvestmentStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
} 