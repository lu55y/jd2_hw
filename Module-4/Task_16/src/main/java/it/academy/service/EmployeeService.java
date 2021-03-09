package it.academy.service;

import it.academy.data.EmployeeRepository;
import it.academy.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    @Transactional
    public String addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
