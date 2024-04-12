package com.springdatajdbcdemo.util.mappers;

import com.springdatajdbcdemo.dto.Roles;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoleMapper implements RowMapper<Roles> {
    @Override
    public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Roles.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .build();
    }
}
