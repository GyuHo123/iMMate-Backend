package com.immate.immate.repo;

import com.immate.immate.entity.BrokerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrokerAccountRepository extends JpaRepository<BrokerAccount, Long> {
    List<BrokerAccount> findByUserId(Long userId);
} 