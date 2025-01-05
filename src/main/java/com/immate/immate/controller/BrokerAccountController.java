package com.immate.immate.controller;

import com.immate.immate.dto.BrokerAccountResponse;
import com.immate.immate.service.BrokerAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "증권사 계좌", description = "증권사 계좌 관련 API")
@RestController
@RequestMapping("/api/broker-accounts")
@RequiredArgsConstructor
public class BrokerAccountController {
    private final BrokerAccountService brokerAccountService;

    @Operation(summary = "증권사 계좌 목록 조회", description = "로그인한 사용자의 증권사 계좌 목록과 보유 주식 정보를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<BrokerAccountResponse>> getBrokerAccounts(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        return ResponseEntity.ok(brokerAccountService.getBrokerAccounts(userEmail));
    }
} 