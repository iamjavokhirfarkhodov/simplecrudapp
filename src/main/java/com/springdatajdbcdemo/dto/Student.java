package com.springdatajdbcdemo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @JsonProperty("id")
    private UUID user_id;
    private String firstname;
    private String lastname;
    private LocalDate birth_date;
    private Integer faculty;
    private Integer year_of_study;
    private String address;

    @Override
    public String toString() {
        return "Student{" +
                "user_id=" + user_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birth_date +
                ", faculty=" + faculty +
                ", year_of_study=" + year_of_study +
                ", address='" + address + '\'' +
                '}';
    }
}
