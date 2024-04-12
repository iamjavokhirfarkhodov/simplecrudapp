package com.springdatajdbcdemo.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsernameAndPassword {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UsernameAndPassword{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
