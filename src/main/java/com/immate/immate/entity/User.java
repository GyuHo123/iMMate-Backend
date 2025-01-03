package com.immate.immate.entity;

import com.immate.immate.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private Long phoneNumber;

    @Column(nullable = false)
    private String password;

    public User(String name, String nickName, Long phoneNumber, String Password){
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto(user.getName(), user.getNickName(), user.getPhoneNumber(), user.getPassword());
        return userDto;
    }
}
