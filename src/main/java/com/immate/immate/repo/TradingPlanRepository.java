package com.immate.immate.repo;

import com.immate.immate.entity.investment.TradingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TradingPlanRepository extends JpaRepository<TradingPlan, Long> {
} 