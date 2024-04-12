package com.springdatajdbcdemo.services.student;

import com.springdatajdbcdemo.dto.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    int update(UUID uuid, Student student);
    int delete(UUID uuid);
    Optional<Student> getByID(UUID id);

}
