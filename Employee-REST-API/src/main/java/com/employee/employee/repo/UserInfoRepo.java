package com.employee.employee.repo;

import com.employee.employee.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserInfoRepo extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByName(String username);

    @Query("{'email' : ?0}")
    Optional<UserInfo> findByEmail(String email);
}
