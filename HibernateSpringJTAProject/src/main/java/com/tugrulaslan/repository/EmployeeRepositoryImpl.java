package com.tugrulaslan.repository;

import com.tugrulaslan.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Tugrul on 7/2/2017.
 */
@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = null;
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT c FROM Employee AS c", Employee.class);
        employeeList = query.getResultList();
        return employeeList;
    }

}
