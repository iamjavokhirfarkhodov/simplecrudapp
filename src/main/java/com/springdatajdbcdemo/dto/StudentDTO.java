package com.springdatajdbcdemo.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birth_date;
    private Integer faculty;
    private String address;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birth_date +
                ", faculty=" + faculty +
                ", address='" + address + '\'' +
                '}';
    }
}
