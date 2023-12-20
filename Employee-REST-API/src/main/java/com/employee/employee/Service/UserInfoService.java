package com.employee.employee.Service;

import com.employee.employee.entity.UserInfo;
import com.employee.employee.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserInfo addUser(UserInfo userInfo){
        userInfo.setId(UUID.randomUUID().toString().split("-")[0]);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return repo.save(userInfo);
    }
}
