package com.springdatajdbcdemo.services.management;

import com.springdatajdbcdemo.dto.*;
import com.springdatajdbcdemo.util.ResultMessage;
import com.springdatajdbcdemo.util.mappers.FacultyMapper;
import com.springdatajdbcdemo.util.mappers.RoleMapper;
import com.springdatajdbcdemo.util.mappers.StudentMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class ManagementRepositoryImplement implements ManagementRepository {
    private JdbcTemplate template;
    private PasswordEncoder encoder;

    public ManagementRepositoryImplement(JdbcTemplate template, PasswordEncoder encoder) {
        this.template = template;
        this.encoder = encoder;
    }

    //Users
    @Override
    public UUID create(UserDTO userDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        UUID id = null;
        String sql = "insert into users(username, password, created_at) values(?,?,?)";
        template.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userDTO.getUsername());
            statement.setString(2, encoder.encode(userDTO.getPassword()));
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            return statement;
        }, keyHolder);
        id = (UUID) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        String sqlDefaultRole = "insert into user_roles(user_id, role_id) values(?,?)";
        template.update(sqlDefaultRole, id, 8);
        return id;
    }

    //Students DONE
    @Override
    public Student getStudentByID(UUID id) {
        String sql = "select * from student where user_id = ?";
        return template.queryForObject(sql, new StudentMapper(), id);

    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from student";
        return template.query(sql, new StudentMapper());
    }

    @Override
    public List<Student> getAllStudentsByNames(String pattern) {
        String sql = "select * from student where firstname ilike ?";
        return template.query(sql, new StudentMapper(), "%" + pattern + "%");
    }

    @Override
    public int disableUserById(UUID id) {
        String sql = "update users set is_enabled = false where id = ?";
        return template.update(sql, id);
    }

    @Override
    public int enableUserById(UUID id) {
        String sql = "update users set is_enabled = true where id = ?";
        return template.update(sql, id);
    }


    //Faculty DONE

    @Override
    public int createFaculty(Faculty faculty) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into faculty(name) values(?)";
        template.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, faculty.getName());
            return statement;
        }, keyHolder);
        return (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public List<Faculty> getAllFaculty() {
        String facultyQuery = "select * from faculty";
        return template.query(facultyQuery, new FacultyMapper());
    }

    @Override
    public FacultyWithStudents getFacultyWithStudents(Integer id) {
        String studentsQuery = "select * from student s inner join faculty f on s.faculty = f.id where f.id = ?";
        String facultyQuery = "select * from faculty where id  = ?";
        Faculty faculty = template.queryForObject(facultyQuery, new FacultyMapper(), id);
        List<Student> students = template.query(studentsQuery, new StudentMapper(), id);
        assert faculty != null;
        return FacultyWithStudents.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .students(students)
                .build();
    }

    @Override
    public int deleteFacultyById(Integer id) {
        String sql = "delete from faculty where id = ?";
        return template.update(sql, id);
    }

    @Override
    public int updateFacultyById(Integer id, Faculty faculty) {
        String sql = "update faculty set name = ? where id = ?";
        return template.update(sql, faculty.getName(), id);
    }

    //Roles DONE
    @Override
    public List<Roles> getRoles() {
        String sql = "select * from roles";
        return template.query(sql, new RoleMapper());
    }

    @Override
    public int createRole(Roles role) {
        KeyHolder key = new GeneratedKeyHolder();
        String sql = "insert into roles(name) values(?)";
        template.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, role.getName());
            return statement;
        }, key);
        return (int) Objects.requireNonNull(key.getKeys()).get("id");
    }

    @Override
    public Roles getRoleById(Integer id) {
        String sql = "select * from roles where id = ?";
        return template.queryForObject(sql, new RoleMapper(), id);
    }

    @Override
    public int deleteRoleById(Integer id) {
        String sql = "delete from roles where id = ?";
        return template.update(sql, id);
    }

    @Override
    public int updateRoleById(Integer id, Roles roles) {
        String sql = "update roles set name = ? where id = ?";
        return template.update(sql, roles.getName(), id);
    }

    @Override
    public int assignRoleToUser(UUID id, Integer role_id) {
        String sql = "insert into user_roles(user_id, role_id) values(?,?)";
        return template.update(sql, id, role_id);
    }

    @Override
    public int removeRoleFromUser(UUID id, Integer role_id) {
        String sql = "delete from user_roles where user_id = ? and role_id = ?";
        return template.update(sql, id, role_id);
    }
}
