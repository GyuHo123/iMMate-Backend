package com.immate.immate.repo;

import com.immate.immate.entity.vote.StockVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StockVoteRepository extends JpaRepository<StockVote, Long> {
    Optional<StockVote> findByPlanIdAndStockIdAndUserId(Long planId, Long stockId, Long userId);

    @Query("SELECT v.voteOption, COUNT(v) FROM StockVote v WHERE v.plan.id = :planId AND v.stock.id = :stockId GROUP BY v.voteOption")
    List<Object[]> countVotesByOptionAndStock(@Param("planId") Long planId, @Param("stockId") Long stockId);
} 