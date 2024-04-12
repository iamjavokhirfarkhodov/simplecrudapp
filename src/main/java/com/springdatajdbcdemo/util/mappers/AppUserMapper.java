package com.springdatajdbcdemo.util.mappers;

import com.springdatajdbcdemo.authentication.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUserMapper implements RowMapper<AppUser> {
    private String username;
    private List<? extends GrantedAuthority> authorities;

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AppUser.builder()
                .username(rs.getString(2))
                .password(rs.getString(3))
                .authorities(getAuthorities())
                .isAccountNonExpired(rs.getBoolean(4))
                .isAccountNonLocked(rs.getBoolean(5))
                .isCredentialsNonExpired(rs.getBoolean(6))
                .isEnabled(rs.getBoolean(7))
                .build();
    }
}
