package com.tugrulaslan.repository;

import com.tugrulaslan.entity.Employee;

import java.util.List;

/**
 * Created by Tugrul on 7/2/2017.
 */
public interface EmployeeRepository {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
}
