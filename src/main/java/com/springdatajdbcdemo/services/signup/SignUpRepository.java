package com.springdatajdbcdemo.services.signup;

import com.springdatajdbcdemo.dto.Student;
import com.springdatajdbcdemo.dto.StudentDTO;

import java.util.UUID;

public interface SignUpRepository {
    UUID create(StudentDTO student);
}
