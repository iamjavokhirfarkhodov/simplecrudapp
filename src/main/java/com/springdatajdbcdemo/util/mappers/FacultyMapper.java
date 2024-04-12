package com.springdatajdbcdemo.util.mappers;

import com.springdatajdbcdemo.dto.Faculty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FacultyMapper implements RowMapper<Faculty> {
    @Override
    public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Faculty.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .build();
    }
}
