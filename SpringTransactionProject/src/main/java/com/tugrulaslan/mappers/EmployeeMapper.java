package com.tugrulaslan.mappers;

import com.tugrulaslan.pojos.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tugrul on 6/28/2017.
 */
public class EmployeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee eemployee = new Employee();
        eemployee.setId(resultSet.getInt("id"));
        eemployee.setName(resultSet.getString("name"));
        eemployee.setDepartment(resultSet.getString("department"));
        return eemployee;
    }
}
