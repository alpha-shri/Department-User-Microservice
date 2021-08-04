package com.department.services;

import com.department.dao.DepartmentRepository;
import com.department.entities.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository dao;

    public Department addNewDepartmentService(Department newDepartment) {
        log.info("Inside DepartmentService class: addNewDepartmentService(): " +newDepartment);

        return dao.save(newDepartment);
    }

    public Department findByIdService(Long departmentId) throws Exception {

        Optional<Department> optionalDepartment = dao.findById(departmentId);

        if(!optionalDepartment.isPresent()){
            throw new Exception("Department not found");
        }

        return optionalDepartment.get();
    }
}
