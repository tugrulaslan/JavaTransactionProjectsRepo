package com.tugrulaslan.programmatictransaction;

import com.tugrulaslan.mappers.EmployeeMapper;
import com.tugrulaslan.pojos.Employee;
import com.tugrulaslan.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by Tugrul on 6/28/2017.
 */
@Transactional
public class ProgrammaticTransactionRepositoryImpl implements EmployeeRepository {
    private final Logger logger = LoggerFactory.getLogger(ProgrammaticTransactionRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager platformTransactionManager;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    public void saveEmployee(String name, String department) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            String sql1 = "insert into Employee (name, department) values (?, ?)";
            jdbcTemplate.update(sql1, name, department);
            platformTransactionManager.commit(transactionStatus);
            logger.info("Saved Employee {} " + name, department);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            platformTransactionManager.rollback(transactionStatus);
        }
    }

    public List<Employee> getAllEmployees() {
        String SQL = "select * from Employee";
        List<Employee> employees = jdbcTemplate.query(SQL, new EmployeeMapper());
        logger.debug("total employee amount: " + employees.size());
        return employees;
    }
}
