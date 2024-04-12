package com.springdatajdbcdemo.controllers;

import com.springdatajdbcdemo.dto.Student;
import com.springdatajdbcdemo.services.student.StudentService;
import com.springdatajdbcdemo.util.ResultMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResultMessage> updateStudent(@PathVariable("id") UUID id, @RequestBody Student student) {
        return studentService.updateById(id, student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultMessage> getStudentById(@PathVariable("id") UUID id) {
        return studentService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultMessage> deleteStudentById(@PathVariable("id") UUID id) {
        return studentService.deleteByID(id);
    }
}
