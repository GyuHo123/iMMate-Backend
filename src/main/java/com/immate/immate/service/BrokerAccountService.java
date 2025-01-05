package com.immate.immate.service;

import com.immate.immate.dto.BrokerAccountResponse;
import com.immate.immate.dto.StockResponse;
import com.immate.immate.entity.BrokerAccount;
import com.immate.immate.entity.Stock;
import com.immate.immate.repo.BrokerAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrokerAccountService {
    private final BrokerAccountRepository brokerAccountRepository;

    @Transactional(readOnly = true)
    public List<BrokerAccountResponse> getBrokerAccounts(Long userId) {
        List<BrokerAccount> accounts = brokerAccountRepository.findByUserId(userId);
        return accounts.stream()
                .map(this::convertToBrokerAccountResponse)
                .collect(Collectors.toList());
    }

    private BrokerAccountResponse convertToBrokerAccountResponse(BrokerAccount account) {
        List<StockResponse> stocks = account.getStocks().stream()
                .map(this::convertToStockResponse)
                .collect(Collectors.toList());

        return BrokerAccountResponse.builder()
                .brokerName(account.getBrokerName())
                .investmentAmount(account.getInvestmentAmount())
                .evaluationAmount(account.getEvaluationAmount())
                .stocks(stocks)
                .build();
    }

    private StockResponse convertToStockResponse(Stock stock) {
        return StockResponse.builder()
                .stockName(stock.getStockName())
                .investmentAmount(stock.getInvestmentAmount())
                .evaluationAmount(stock.getEvaluationAmount())
                .quantity(stock.getQuantity())
                .build();
    }
} 