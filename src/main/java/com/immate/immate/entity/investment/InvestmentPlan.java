package com.immate.immate.entity.investment;

import com.immate.immate.entity.user.Crew;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class InvestmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isActive;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvestmentStock> stocks = new ArrayList<>();

    public void addStock(InvestmentStock stock) {
        stocks.add(stock);
        stock.setPlan(this);
    }

    public void removeStock(InvestmentStock stock) {
        stocks.remove(stock);
        stock.setPlan(null);
    }
} 