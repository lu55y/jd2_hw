package it.academy.data;

import it.academy.pojos.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepository{

    public List<Employee> findAllEmployee();


    String save(Employee employee);
}
