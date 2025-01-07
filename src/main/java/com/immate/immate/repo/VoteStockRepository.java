package com.immate.immate.repo;

import com.immate.immate.entity.vote.VoteStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteStockRepository extends JpaRepository<VoteStock, Long> {
    List<VoteStock> findByCrewId(Long crewId);
}
