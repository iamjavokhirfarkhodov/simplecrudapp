package com.springdatajdbcdemo.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacultyWithStudents {
    private Integer id;
    private String name;
    private List<Student> students;
}
