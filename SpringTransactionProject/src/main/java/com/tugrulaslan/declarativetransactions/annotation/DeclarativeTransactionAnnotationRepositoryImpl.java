package com.tugrulaslan.declarativetransactions.annotation;

import com.tugrulaslan.mappers.EmployeeMapper;
import com.tugrulaslan.pojos.Employee;
import com.tugrulaslan.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tugrul on 6/28/2017.
 */
@Transactional
public class DeclarativeTransactionAnnotationRepositoryImpl implements EmployeeRepository {
    private final Logger logger = LoggerFactory.getLogger(DeclarativeTransactionAnnotationRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveEmployee(String name, String department) {
        try {
            String sql1 = "insert into Employee (name, department) values (?, ?)";
            jdbcTemplate.update(sql1, name, department);
            logger.info("Saved Employee {} " + name, department);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        String SQL = "select * from Employee";
        List<Employee> employees = jdbcTemplate.query(SQL, new EmployeeMapper());
        logger.debug("total employee amount: " + employees.size());
        return employees;
    }
}
