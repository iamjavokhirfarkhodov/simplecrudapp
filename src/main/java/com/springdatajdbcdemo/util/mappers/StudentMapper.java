package com.springdatajdbcdemo.util.mappers;

import com.springdatajdbcdemo.dto.Student;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder()
                .user_id(UUID.fromString(rs.getString(1)))
                .firstname(rs.getString(2))
                .lastname(rs.getString(3))
                .birth_date(rs.getDate(4).toLocalDate())
                .faculty(rs.getInt(5))
                .year_of_study(rs.getInt(6))
                .address(rs.getString(7))
                .build();
    }
}
