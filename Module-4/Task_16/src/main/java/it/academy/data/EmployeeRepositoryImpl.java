package it.academy.data;

import it.academy.pojos.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return getSessionFactory().getCurrentSession()
                .createQuery("from Employee",Employee.class)
                .list();
    }

    @Override
    public String save(Employee employee) {
        Session session=getSessionFactory().getCurrentSession();
        String id =(String) session.save(employee);
        return id;
    }
}
