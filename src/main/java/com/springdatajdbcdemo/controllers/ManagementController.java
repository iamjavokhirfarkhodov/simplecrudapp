package com.springdatajdbcdemo.controllers;

import com.springdatajdbcdemo.dto.Faculty;
import com.springdatajdbcdemo.dto.Roles;
import com.springdatajdbcdemo.dto.UserDTO;
import com.springdatajdbcdemo.services.management.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/management")
public class ManagementController {
    private ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    //Users
    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
        return managementService.createUser(dto);
    }

    @PostMapping("/users/enable/{id}")
    public ResponseEntity<?> enableUserById(@PathVariable("id") UUID id) {
        return managementService.enableUserById(id);
    }
    @PostMapping("/users/disable/{id}")
    public ResponseEntity<?> disableUserById(@PathVariable("id") UUID id) {
        return managementService.disableUserById(id);
    }

    //Students
    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        return managementService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") UUID id) {
        return managementService.getStudentById(id);
    }

    @GetMapping("/students/by")
    public ResponseEntity<?> getStudentByName(@RequestParam("name") String name) {
        return managementService.getAllStudentsByName(name);
    }

    //Faculty
    @PostMapping("/faculties")
    public ResponseEntity<?> createFaculties(@RequestBody Faculty faculty) {
        return managementService.createFaculty(faculty);
    }
    @GetMapping("/faculties")
    public ResponseEntity<?> getFaculties() {
        return managementService.getAllFaculties();
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable("id") Integer id) {
        return managementService.getFacultyWithStudents(id);
    }
    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable("id") Integer id) {
        return managementService.deleteFacultyById(id);
    }
    @PutMapping("/faculties/{id}")
    public ResponseEntity<?> updateFacultyById(@PathVariable("id") Integer id, @RequestBody Faculty faculty) {
        return managementService.updateFacultyById(id, faculty);
    }


    //Roles
    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody Roles role){
        return managementService.createRole(role);
    }

    @PostMapping("/roles/assign/{id}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable("id") UUID user_id, @RequestBody Roles role){
        return managementService.assignRoleToUser(user_id, role.getId());
    }
    @DeleteMapping("/roles/remove/{id}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable("id") UUID user_id, @RequestBody Roles role){
        System.out.println(role);
        return managementService.removeRoleFromUser(user_id, role.getId());
    }
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(){
        return managementService.getRoles();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") Integer id){
        return managementService.getRoleById(id);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("id") Integer id){
        return managementService.deleteRoleById(id);
    }
    @PutMapping("/roles/{id}")
    public ResponseEntity<?> updateRoleById(@PathVariable("id") Integer id, @RequestBody Roles role){
        return managementService.updateRoleById(id, role);
    }
}
