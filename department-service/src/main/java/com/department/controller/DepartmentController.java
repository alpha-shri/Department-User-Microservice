package com.department.controller;

import com.department.entities.Department;
import com.department.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping("/add")
    public ResponseEntity<Department> addNewDepartment(@RequestBody Department newDepartment){
        log.info("Inside DepartmentController class: addNewDepartment(): " +newDepartment);
        Department department = service.addNewDepartmentService(newDepartment);

        return new ResponseEntity(department, HttpStatus.OK);
    }

    @GetMapping("/find/{departmentId}")
    public ResponseEntity<Department> findById(@PathVariable Long departmentId) throws Exception {

        Department department = service.findByIdService(departmentId);
        return new ResponseEntity(department, HttpStatus.OK);

    }


}
