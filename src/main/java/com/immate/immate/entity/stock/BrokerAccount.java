package com.immate.immate.entity.stock;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.immate.immate.entity.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "broker_accounts")
public class BrokerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String brokerName;

    @Column(nullable = false)
    private Long investmentAmount;

    @Column(nullable = false)
    private Long evaluationAmount;

    @OneToMany(mappedBy = "brokerAccount", cascade = CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();
} 