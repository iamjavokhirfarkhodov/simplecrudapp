package com.springdatajdbcdemo.authentication;

import com.springdatajdbcdemo.dto.Roles;
import com.springdatajdbcdemo.dto.UserDTO;
import com.springdatajdbcdemo.services.management.ManagementService;
import com.springdatajdbcdemo.util.mappers.AppUserMapper;
import com.springdatajdbcdemo.util.mappers.RoleMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserRepositoryImplement implements AppUserRepository {
    private JdbcTemplate template;
    private ManagementService service;

    private PasswordEncoder encoder;

    public AppUserRepositoryImplement(JdbcTemplate template, ManagementService service, PasswordEncoder encoder) {
        this.template = template;
        this.service = service;
        this.encoder = encoder;
    }

    @Override
    public AppUser getUserByUserName(String username) {
        String sql = "select * from users where username = ?";
            return template.queryForObject(sql, new AppUserMapper(username, getAuthorities(username)), username);
    }

    @Override
    public List<Roles> getRolesByUsername(String username) {
        String sql = "select r.id, r.name from users u inner join user_roles ur on u.id = ur.user_id inner join roles r on ur.role_id = r.id where u.username = ?";
        return template.query(sql, new RoleMapper(), username);
    }

    private List<? extends GrantedAuthority> getAuthorities(String username) {
        List<Roles> rolesByID = getRolesByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        rolesByID.forEach(role -> roles.add(new SimpleGrantedAuthority(role.getName())));
        return roles;
    }

}
