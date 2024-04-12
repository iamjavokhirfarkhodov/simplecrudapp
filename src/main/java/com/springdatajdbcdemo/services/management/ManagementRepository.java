package com.springdatajdbcdemo.services.management;

import com.springdatajdbcdemo.dto.*;

import java.util.List;
import java.util.UUID;

public interface ManagementRepository {

    //Users
    UUID create(UserDTO userDTO);

    int enableUserById(UUID id);

    int disableUserById(UUID id);

    //Student
    Student getStudentByID(UUID id);

    List<Student> getAllStudents();

    List<Student> getAllStudentsByNames(String pattern);


    //Faculty

    FacultyWithStudents getFacultyWithStudents(Integer id);

    int createFaculty(Faculty faculty);

    List<Faculty> getAllFaculty();

    int deleteFacultyById(Integer id);

    int updateFacultyById(Integer id, Faculty faculty);

    //Roles
    int createRole(Roles role);

    List<Roles> getRoles();

    Roles getRoleById(Integer id);

    int deleteRoleById(Integer id);

    int updateRoleById(Integer id, Roles roles);

    int assignRoleToUser(UUID id, Integer role_id);
    int removeRoleFromUser(UUID id, Integer role_id);

}
