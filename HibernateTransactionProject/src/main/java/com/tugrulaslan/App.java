package com.tugrulaslan;

import com.tugrulaslan.dao.EmployeeDAO;
import com.tugrulaslan.dao.EmployeeDAOImpl;
import com.tugrulaslan.entity.Employee;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee newEmployee = new Employee("Tugrul", "IT");
        employeeDAO.saveEmployee(newEmployee);

        List<Employee> employees = employeeDAO.getAllEmployee();
        for (Employee employee : employees) {
            System.out.println("Employee Id: " + employee.getId());
            System.out.println("Employee Name:  " + employee.getName());
            System.out.println("Employee Department: " + employee.getDepartment() + "\n");
        }
    }
}
