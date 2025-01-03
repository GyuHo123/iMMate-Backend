package com.immate.immate.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String nickName;
    private Long phoneNumber;
    private String password;

    public UserDto(String name, String nickName, Long phoneNumber, String password){
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
