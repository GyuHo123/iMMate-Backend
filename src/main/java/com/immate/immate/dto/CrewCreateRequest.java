package com.immate.immate.dto;

import lombok.Data;

@Data
public class CrewCreateRequest {
    private String crewName;
    private String crewDescription;
    private Integer crewMinInvestment;

    public CrewCreateRequest(String crewName, String crewDescription, Integer crewMinInvestment) {
        this.crewName = crewName;
        this.crewDescription = crewDescription;
        this.crewMinInvestment = crewMinInvestment;
    }
}
