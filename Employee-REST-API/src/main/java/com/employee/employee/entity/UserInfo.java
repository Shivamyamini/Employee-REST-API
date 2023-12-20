package com.employee.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String roles;
}
