package com.tugrulaslan.dao;

import com.tugrulaslan.entity.Employee;
import com.tugrulaslan.util.EntityManagerUtil;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Tugrul on 7/7/2017.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
    private static EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void saveEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return entityManager.createQuery("FROM Employee").getResultList();
    }
}
