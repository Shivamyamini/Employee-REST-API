package com.employee.employee.Service;

import com.employee.employee.Modal.Employee;
import com.employee.employee.repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.employee.employee.Modal.Employee.SEQUENCE_NAME;

@Service
public class EmpService {
    @Autowired
    private EmpRepo repo;

    @Autowired
    private SequenceGeneratorService service;

    public Employee addEmp(Employee emp){
        emp.setId(String.valueOf(service.getSequenceNumber(SEQUENCE_NAME)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        emp.setCreateTime(dtf.format(now));
        emp.setUpdateTime(dtf.format(now));
        return repo.save(emp);
    }

    public List<Employee> findAllEmp(){
        return repo.findAll();
    }

    public Employee getEmpById(String empId){
        return repo.findById(empId).get();
    }

    public Employee getEmpByName(String name){
        return repo.getEmployByName(name);
    }

    public Employee updateEmp(Employee employee){
        Employee updateEmployee = repo.findById(employee.getId()).get();

        if(employee.getName() !=null )
            updateEmployee.setName(employee.getName());
        if(employee.getSalary() !=0 )
            updateEmployee.setSalary(employee.getSalary());
        if(employee.getBranch() !=null )
            updateEmployee.setBranch(employee.getBranch());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        employee.setUpdateTime(dtf.format(now));

        return repo.save(updateEmployee);
    }

    public String deleteEmp(String empId){
        repo.deleteById(empId);
        return empId +" employee deleted from dashboard ";
    }
}
