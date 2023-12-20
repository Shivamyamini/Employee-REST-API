package com.employee.employee.Modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EmployeeInfo")
@JsonPropertyOrder({"_id", "name", "salary", "branch", "create_time", "update_time"})
public class Employee {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("salary")
    private long salary;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("update_time")
    private String updateTime;
}
