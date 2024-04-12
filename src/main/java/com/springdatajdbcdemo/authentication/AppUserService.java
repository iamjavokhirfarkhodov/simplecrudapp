package com.springdatajdbcdemo.authentication;

import com.springdatajdbcdemo.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.getUserByUserName(username);
    }

//    public ResponseEntity<?> createUser(UserDTO userDTO){
//        appUserRepository.create(userDTO);
//        return ResponseEntity.status(201).body("");
//    }
}
