package com.springdatajdbcdemo.controllers;

import com.springdatajdbcdemo.dto.StudentDTO;
import com.springdatajdbcdemo.services.signup.SignUpService;
import com.springdatajdbcdemo.util.ResultMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/student")
    public ResponseEntity<ResultMessage> createStudent(@RequestBody StudentDTO student) {
        return signUpService.createNewStudent(student);
    }
}
