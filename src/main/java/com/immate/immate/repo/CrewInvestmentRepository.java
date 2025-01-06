package com.immate.immate.repo;

import com.immate.immate.entity.investment.CrewInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CrewInvestmentRepository extends JpaRepository<CrewInvestment, Long> {
} 