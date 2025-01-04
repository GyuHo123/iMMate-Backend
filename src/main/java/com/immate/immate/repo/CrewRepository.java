package com.immate.immate.repo;

import com.immate.immate.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    List<Crew> findByUsers_Id(Long userId);
    
    List<Crew> findByInvestmentStyleOrderByCrewYieldDesc(String style);
    
    List<Crew> findAllByOrderByCrewYieldDesc();
} 