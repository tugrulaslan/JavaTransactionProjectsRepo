package com.tugrulaslan.declarativetransactions.xml;

import com.tugrulaslan.pojos.Employee;
import com.tugrulaslan.repository.EmployeeRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DeclarativeTransactionXMLMainApp{
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("declarative-transaction-xml-beans.xml");
        EmployeeRepository employeeRepository = (EmployeeRepository) applicationContext.getBean("employeeRepository");

        employeeRepository.saveEmployee("Tugrul", "IT");
        employeeRepository.saveEmployee("Vika", "HR");
        employeeRepository.saveEmployee("Lesha", "Finance");

        List<Employee> employeeData = employeeRepository.getAllEmployees();
        for (Employee employee : employeeData) {
            System.out.println("Employee Id: " + employee.getId());
            System.out.println("Employee Name:  " + employee.getName());
            System.out.println("Employee Department: " + employee.getDepartment() + "\n");
        }
    }
}
