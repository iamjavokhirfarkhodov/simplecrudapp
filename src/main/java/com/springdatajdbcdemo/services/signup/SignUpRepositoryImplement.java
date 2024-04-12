package com.springdatajdbcdemo.services.signup;

import com.springdatajdbcdemo.dto.StudentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class SignUpRepositoryImplement implements SignUpRepository {
    private JdbcTemplate template;
    private PasswordEncoder encoder;

    public SignUpRepositoryImplement(JdbcTemplate template, PasswordEncoder encoder) {
        this.template = template;
        this.encoder = encoder;
    }

    //Sign up DONE
    @Override
    public UUID create(StudentDTO student) {
        String sqlUser = "insert into users(username, password, created_at) values(?,?,?)";
        String sqlStudent = "insert into student(user_id, firstname, lastname, birth_date, faculty, address) values(?,?,?,?,?,?)";

        String sqlAsssignRole = "insert into user_roles(user_id, role_id) values(?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        UUID user_id = null;
        if (student.getFirstname() != null &&
                student.getLastname() != null &&
                student.getBirth_date() != null &&
                student.getFaculty() != null &&
                student.getAddress() != null
        ) {
            template.update(con -> {
                PreparedStatement statement = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, student.getUsername());
                statement.setString(2, encoder.encode(student.getPassword()));
                statement.setDate(3, Date.valueOf(LocalDate.now()));
                return statement;
            }, keyHolder);
            user_id = (UUID) Objects.requireNonNull(keyHolder.getKeys()).get("id");
            template.update(sqlStudent, user_id, student.getFirstname(), student.getLastname(), student.getBirth_date(), student.getFaculty(), student.getAddress());
            template.update(sqlAsssignRole, user_id, 2);
        }
        return user_id;
    }
}
