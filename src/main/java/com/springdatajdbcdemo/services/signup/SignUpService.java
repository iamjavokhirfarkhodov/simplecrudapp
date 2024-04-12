package com.springdatajdbcdemo.services.signup;

import com.springdatajdbcdemo.dto.Student;
import com.springdatajdbcdemo.dto.StudentDTO;
import com.springdatajdbcdemo.util.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.springdatajdbcdemo.util.ResultMessage.getResultMessage;

@Service
public class SignUpService {
    private SignUpRepositoryImplement signUpRepo;

    public SignUpService(SignUpRepositoryImplement signUpRepo) {
        this.signUpRepo = signUpRepo;
    }

    public ResponseEntity<ResultMessage> createNewStudent(StudentDTO student) {
        UUID uuid = signUpRepo.create(student);
        if (uuid == null) {
            return getResultMessage(HttpStatus.BAD_REQUEST, "Provided null values!");
        }
        return getResultMessage(HttpStatus.CREATED, "Student with id %s created!".formatted(uuid));
    }
}
