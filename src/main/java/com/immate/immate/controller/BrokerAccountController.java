package com.immate.immate.controller;

import com.immate.immate.dto.BrokerAccountResponse;
import com.immate.immate.service.BrokerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/broker-accounts")
@RequiredArgsConstructor
public class BrokerAccountController {
    private final BrokerAccountService brokerAccountService;

    @GetMapping
    public ResponseEntity<List<BrokerAccountResponse>> getBrokerAccounts(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(brokerAccountService.getBrokerAccounts(userId));
    }
} 