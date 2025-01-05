package com.immate.immate.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserInfo {
    private String email;
    private String name;
    private String investmentStyle;

    public UserInfo(String email, String name, String investmentStyle) {
        this.email = email;
        this.name = name;
        this.investmentStyle = investmentStyle;
    }
}
