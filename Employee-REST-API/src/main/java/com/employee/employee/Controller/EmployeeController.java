package com.employee.employee.Controller;

import com.employee.employee.Modal.AuthRequest;
import com.employee.employee.Modal.Employee;
import com.employee.employee.Service.EmpService;
import com.employee.employee.Service.UserInfoService;
import com.employee.employee.Service.jwtService;
import com.employee.employee.entity.JwtToken;
import com.employee.employee.entity.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmpService service;

    @Autowired
    private UserInfoService serviceUser;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtService serviceJwt;


    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Access details of all the employees in the database",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public List<Employee> getEmp(){
        return service.findAllEmp();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Getting the data of an employee by providing their id",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public Employee getEmpById(@PathVariable String id){
        return service.getEmpById(id);
    }

    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Adding an employee to the database",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public Employee postEmp(@RequestBody Employee emp){
        return service.addEmp(emp);
    }

    @GetMapping("/{name}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Getting the details of an employee by their name",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public Employee getEmpByName(@PathVariable String name){
        return service.getEmpByName(name);
    }

    @PutMapping("/update-emp")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "updating the details of an employee by their id",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public Employee updateEmp(@RequestBody Employee emp){
        return service.updateEmp(emp);
    }

    @DeleteMapping("/delete-emp/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "deleting an employee from the database",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "successfulSignIn"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI")
            })
    public String deleteEmp(@PathVariable String id){
        return service.deleteEmp(id);
    }

    @PostMapping("/add-user")
    public UserInfo addNewUser(@RequestBody UserInfo info){
        return serviceUser.addUser(info);

    }
    @PostMapping("/authenticate")
    public JwtToken authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        JwtToken token = new JwtToken();
        token.setEmail(authRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            token.setToken(serviceJwt.generateToken(authRequest.getEmail()));
        }else{
            throw new UsernameNotFoundException("Invalid user request!!");
        }
        return token;
    }
}