package com.employee.employee.Service;

import com.employee.employee.entity.UserInfo;
import com.employee.employee.entity.UserInfoUserDetails;
import com.employee.employee.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userinfo = userInfoRepo.findByEmail(username);

        return userinfo.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found " + username));

    }
}
