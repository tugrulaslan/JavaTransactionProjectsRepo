package com.tugrulaslan.repository;

import com.tugrulaslan.pojos.Employee;

import java.util.List;

/**
 * Created by Tugrul on 6/28/2017.
 */
public interface EmployeeRepository {
    void saveEmployee(String name, String department);
    List<Employee> getAllEmployees();
}
