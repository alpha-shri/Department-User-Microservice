package com.user.services;

import com.user.dao.UserRepository;
import com.user.entities.Department;
import com.user.entities.User;
import com.user.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository dao;

    @Autowired
    private RestTemplate restTemplate;


    public User addNewUserService(User newUser) {
        log.info("Inside UserService class: addNewUserService(): " +newUser);

        return dao.save(newUser);
    }

    public ResponseVO findByIdService(Long userId) throws Exception {

        ResponseVO responseVO = new ResponseVO();

        User user = dao.findById(userId).get();

        log.info("User -> " +user);

//        String url_dept = "http://localhost:3000/departments/find/"+ user.getDepartmentId();

        String url_dept = "http://DEPARTMENT-SERVICE/departments/find/"+ user.getDepartmentId();

        Department department = restTemplate.getForObject(url_dept, Department.class);

        log.info("Department: " +department);

        responseVO.setUser(user);
        responseVO.setDepartment(department);

        System.out.println(responseVO);

        return responseVO;
    }

    public User findUserByUsernameService(String name){
        return dao.findByName(name)
                       .orElseThrow(() -> {
                           return new RuntimeException("Cannot find user by name: " + name);
                       });

    }
}
