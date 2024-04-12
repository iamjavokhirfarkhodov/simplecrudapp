package com.springdatajdbcdemo.services.management;

import com.springdatajdbcdemo.dto.Faculty;
import com.springdatajdbcdemo.dto.Roles;
import com.springdatajdbcdemo.dto.UserDTO;
import com.springdatajdbcdemo.util.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.springdatajdbcdemo.util.ResultMessage.getResultMessage;

@Service
public class ManagementService {
    private ManagementRepositoryImplement managementRepo;

    public ManagementService(ManagementRepositoryImplement managementRepo) {
        this.managementRepo = managementRepo;
    }

    //Users
    public ResponseEntity<?> createUser(UserDTO dto) {
        return getResultMessage(HttpStatus.CREATED, "User with id %s created!".formatted(managementRepo.create(dto)));
    }
    public ResponseEntity<ResultMessage> disableUserById(UUID id){
        if (managementRepo.disableUserById(id) == 0){
            return getResultMessage(HttpStatus.OK, "Student with id %s not disabled. Student not found!".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Student with id %s disabled.".formatted(id));
    }
    public ResponseEntity<ResultMessage> enableUserById(UUID id){
        if (managementRepo.enableUserById(id) == 0){
            return getResultMessage(HttpStatus.OK, "Student with id %s not enabled. Student not found!".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Student with id %s enabled.".formatted(id));
    }


    //Students DONE
    public ResponseEntity<ResultMessage> getAllStudents() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultMessage.builder().message(HttpStatus.OK.getReasonPhrase()).statusCode(HttpStatus.OK.value()).data(managementRepo.getAllStudents()).build());
    }

    public ResponseEntity<ResultMessage> getStudentById(UUID id) {
        return getResultMessage(HttpStatus.OK, managementRepo.getStudentByID(id));
    }

    public ResponseEntity<ResultMessage> getAllStudentsByName(String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultMessage.builder()
                        .message(HttpStatus.OK.getReasonPhrase())
                        .statusCode(HttpStatus.OK.value())
                        .data(managementRepo.getAllStudentsByNames(name))
                        .build()
                );
    }


    //Faculty DONE
    public ResponseEntity<?> createFaculty(Faculty faculty) {
        return getResultMessage(HttpStatus.CREATED, Faculty.builder().id(managementRepo.createFaculty(faculty)).name(faculty.getName()).build());
    }

    public ResponseEntity<?> getAllFaculties() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultMessage.builder()
                        .message(HttpStatus.OK.getReasonPhrase())
                        .statusCode(HttpStatus.OK.value())
                        .data(managementRepo.getAllFaculty())
                        .build()
                );
    }

    public ResponseEntity<ResultMessage> getFacultyWithStudents(Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultMessage.builder()
                        .message(HttpStatus.OK.getReasonPhrase())
                        .statusCode(HttpStatus.OK.value())
                        .data(managementRepo.getFacultyWithStudents(id))
                        .build()
                );
    }

    public ResponseEntity<?> deleteFacultyById(Integer id) {
        if (managementRepo.deleteFacultyById(id) == 0) {
            return getResultMessage(HttpStatus.BAD_REQUEST, "Faculty with id %s not found".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Faculty with id %s deleted".formatted(id));
    }

    public ResponseEntity<ResultMessage> updateFacultyById(Integer id, Faculty faculty) {
        if (managementRepo.updateFacultyById(id, faculty) == 0) {
            return getResultMessage(HttpStatus.BAD_REQUEST, "Faculty with id %s not found".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Faculty with id %s updated".formatted(id));
    }


    //Roles DONE
    public ResponseEntity<ResultMessage> createRole(Roles role) {
        return getResultMessage(HttpStatus.CREATED, managementRepo.createRole(role));
    }

    public ResponseEntity<ResultMessage> getRoles() {
        return getResultMessage(HttpStatus.OK, managementRepo.getRoles());
    }

    public ResponseEntity<ResultMessage> getRoleById(Integer id) {
        return getResultMessage(HttpStatus.OK, managementRepo.getRoleById(id));
    }

    public ResponseEntity<ResultMessage> deleteRoleById(Integer id) {
        if (managementRepo.deleteRoleById(id) == 0) {
            return getResultMessage(HttpStatus.BAD_REQUEST, "Role with id %s not deleted. Role not found!".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Role with id %s deleted".formatted(id));
    }

    public ResponseEntity<ResultMessage> updateRoleById(Integer id, Roles roles) {
        if (managementRepo.updateRoleById(id, roles) == 0) {
            return getResultMessage(HttpStatus.BAD_REQUEST, "Role with id %s not found".formatted(id));
        }
        return getResultMessage(HttpStatus.OK, "Role with id %s updated".formatted(id));
    }

    public ResponseEntity<ResultMessage> assignRoleToUser(UUID user_id, Integer role_id){
        managementRepo.assignRoleToUser(user_id, role_id);
        return getResultMessage(HttpStatus.CREATED, "Role with id %s assigned to user id %s".formatted(user_id, role_id));
    }
    public ResponseEntity<ResultMessage> removeRoleFromUser(UUID user_id, Integer role_id){
        managementRepo.removeRoleFromUser(user_id, role_id);
        return getResultMessage(HttpStatus.OK, "Role with id %s removed from user id %s".formatted(user_id, role_id));
    }

}
