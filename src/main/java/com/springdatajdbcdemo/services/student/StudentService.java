package com.springdatajdbcdemo.services.student;

import com.springdatajdbcdemo.dto.Student;
import com.springdatajdbcdemo.util.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.springdatajdbcdemo.util.ResultMessage.getResultMessage;

@Service
public class StudentService {

    private StudentRepositoryImplement studentRepo;

    public StudentService(StudentRepositoryImplement studentRepo) {
        this.studentRepo = studentRepo;
    }


    public ResponseEntity<ResultMessage> getByID(UUID id) {
        Optional<Student> student = studentRepo.getByID(id);
        if (student.isPresent()) {
            return getResultMessage(HttpStatus.OK, student);
        }
        return getResultMessage(HttpStatus.NOT_FOUND, "Student with %s id not found!".formatted(id));

    }

    public ResponseEntity<ResultMessage> deleteByID(UUID id) {
        int result = studentRepo.delete(id);
        if (result != 0) {
            return getResultMessage(HttpStatus.OK, "Success! Student with %s id deleted.".formatted(id));
        }
        return getResultMessage(HttpStatus.NOT_FOUND, "Student with %s id not found!".formatted(id));
    }

    public ResponseEntity<ResultMessage> updateById(UUID id, Student student) {
        int result = studentRepo.update(id, student);
        if (result != 0) {
            return getResultMessage(HttpStatus.OK, "Success! Student with %s id updated.".formatted(id));
        }
        return getResultMessage(HttpStatus.NOT_FOUND, "Student with %s id not found!".formatted(id));
    }

}
