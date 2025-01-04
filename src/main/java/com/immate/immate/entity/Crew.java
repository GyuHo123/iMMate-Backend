package com.immate.immate.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "crew")
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crew_name")
    private String crewName;

    @Column(name = "investment_style")
    private String investmentStyle;

    @Column(name = "crew_yield")
    private double crewYield;

    @ManyToMany
    @JoinTable(
        name = "crew_user",
        joinColumns = @JoinColumn(name = "crew_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
