package com.immate.immate.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "profit")
@Getter
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Long totalAmount;

    @Column(nullable = false)
    private Long investmentAmount;
}
