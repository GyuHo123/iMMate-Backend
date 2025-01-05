package com.immate.immate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_account_id")
    private BrokerAccount brokerAccount;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private Long investmentAmount;

    @Column(nullable = false)
    private Long evaluationAmount;

    @Column(nullable = false)
    private Long quantity;
} 