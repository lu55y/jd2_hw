package by.it.util;

import by.it.pojos.Address;
import by.it.pojos.Person;
import by.it.pojos.PoneNumber;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HibernateUtil {


    public Integer create(Person person, Session session) {
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            id = session.save(person);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
                throw e;
        }
        return (Integer) id;
    }

//    public void createWithId(Integer id, Integer age, String name, String surname,  PreparedStatement preparedStatement) throws SQLException {
//            preparedStatement.setInt(1, id);
//            preparedStatement.setInt(2, age);
//            preparedStatement.setString(3, name);
//            preparedStatement.setString(4, surname);
//            preparedStatement.executeUpdate();
//    }




    public Person read(Integer id,Session session){
        Person loadedPerson=session.get(Person.class,id);
        if (loadedPerson!=null) {
            return loadedPerson;
        }
        System.out.println("No such Person exists");
        return null;
    }

    public Person update(Integer id, Integer age, String name, String surname,
                         Integer pNumber, Address address, Session session){
        Person loadedPerson=read(id,session);
        final PoneNumber poneNumber = loadedPerson.getPoneNumber();
        poneNumber.setPhoneNumber(pNumber);
        if (loadedPerson!=null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(poneNumber);
                loadedPerson.setId(id);
                loadedPerson.setAge(age);
                loadedPerson.setName(name);
                loadedPerson.setSurname(surname);
                loadedPerson.setAddress(address);
                session.flush();
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
            return loadedPerson;
        }
        return null;
    }

    public void delete(Integer id,Session session){
        final Person loadedPerson = read(id, session);
        Transaction tx=null;
        if (loadedPerson!=null) {
            try {
                tx = session.beginTransaction();
                session.delete(loadedPerson);
                tx.commit();

            } catch (Exception e) {
                System.out.println("some wrong!&ERROR&!");
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }
}
