package com.user.controller;


import com.user.entities.User;
import com.user.services.UserService;
import com.user.vo.ResponseVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    
    @Autowired
    private UserService service;

    private static final String DEPARTMENT_SERVICE = "DEPARTMENT-SERVICE";

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User newUser){
        log.info("Inside UserController class: addNewUser(): " +newUser);
        User User = service.addNewUserService(newUser);

        return new ResponseEntity(User, HttpStatus.OK);
    }

    @GetMapping("/find/{UserId}")
    @CircuitBreaker(name= DEPARTMENT_SERVICE, fallbackMethod = "departmentFallBack")
    public ResponseEntity<ResponseVO> fetchUserWithDeptDetails(@PathVariable Long UserId) throws Exception {
        log.info("Inside UserController class: fetchUserWithDeptDetails(): " +UserId);

        ResponseVO responseVO = service.findByIdService(UserId);
        return new ResponseEntity(responseVO, HttpStatus.OK);

    }

    public ResponseEntity<String> departmentFallBack(Exception ex){
        return new ResponseEntity<String>("DEPARTMENT-SERVICE is down", HttpStatus.OK);
    }


}
