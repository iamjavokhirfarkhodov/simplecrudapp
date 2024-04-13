package com.springdatajdbcdemo.authentication;


import com.springdatajdbcdemo.dto.Roles;
import com.springdatajdbcdemo.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {
    AppUser getUserByUserName(String username);

    List<Roles> getRolesByUsername(String username);

}
