package com.employee.employee.repo;

import com.employee.employee.Modal.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
public interface EmpRepo extends MongoRepository<Employee,String> {

    @Query("{ 'name': ?0 }")
    Employee getEmployByName(String name);
}
