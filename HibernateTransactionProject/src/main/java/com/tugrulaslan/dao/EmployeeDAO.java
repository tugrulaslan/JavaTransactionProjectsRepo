package com.tugrulaslan.dao;

import com.tugrulaslan.entity.Employee;

import java.util.List;

/**
 * Created by Tugrul on 7/7/2017.
 */
public interface EmployeeDAO {
    void saveEmployee(Employee newEmployee);

    List<Employee> getAllEmployee();
}
