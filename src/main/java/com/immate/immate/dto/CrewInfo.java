package com.immate.immate.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CrewInfo {
    private String crewName;
    private String investmentStyle;
    private double crewYield;

    public CrewInfo(String crewName, String investmentStyle, double crewYield) {
        this.crewName = crewName;
        this.investmentStyle = investmentStyle;
        this.crewYield = crewYield;
    }
} 