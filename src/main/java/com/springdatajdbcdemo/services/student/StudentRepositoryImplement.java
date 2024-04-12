package com.springdatajdbcdemo.services.student;

import com.springdatajdbcdemo.dto.Student;
import com.springdatajdbcdemo.util.mappers.StudentMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class StudentRepositoryImplement implements StudentRepository {
    private JdbcTemplate template;

    public StudentRepositoryImplement(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int update(UUID id, Student student) {
        if (student.getFirstname() == null &&
                student.getLastname() == null &&
                student.getBirth_date() == null &&
                student.getFaculty() == null &&
                student.getYear_of_study() == null &&
                student.getAddress() == null
        ) {
            student.getFirstname().isEmpty();
        }
        StringBuilder builder = new StringBuilder("update student set ");
        if (student.getFirstname() != null) {
            builder.append("firstname = ").append("'").append(student.getFirstname()).append("', ");
        }
        if (student.getLastname() != null) {
            builder.append("lastname = ").append("'").append(student.getLastname()).append("', ");
        }
        if (student.getBirth_date() != null) {
            builder.append("birth_date = ").append("'").append(student.getBirth_date()).append("', ");
        }
        if (student.getAddress() != null) {
            builder.append("address = ").append("'").append(student.getAddress()).append("', ");
        }
        builder.append("where user_id = ").append("'").append(id.toString()).append("'");
        String string = builder.toString();
        String s1 = string.substring(0, string.lastIndexOf(','));
        String s2 = string.substring(string.lastIndexOf(',')+1);
        return template.update(s1 + s2);
    }

    @Override
    public int delete(UUID id) {
        String sqlStudent = "delete from student where user_id = ?";
        String sqlUsers = "delete from users where  id = ?";
        String sqlRoles = "delete from user_roles where user_id = ?";
        template.update(sqlRoles, id);
        template.update(sqlStudent, id);
        return template.update(sqlUsers, id);
    }

    @Override
    public Optional<Student> getByID(UUID id) {
        String sql = "select * from student where user_id = ?";
        return Optional.ofNullable(template.queryForObject(sql, new StudentMapper(), id));
    }
}
