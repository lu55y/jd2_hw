package by.it.util;

import by.it.pojos.Address;
import by.it.pojos.Person;
import by.it.pojos.PoneNumber;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

@FixMethodOrder
public class HibernateUtilTest extends BaseTest{

    HibernateUtil hibernateUtil=new HibernateUtil();

    @Test
    public void create() {
        Session session = factory.openSession();
        PoneNumber poneNumber=new PoneNumber();
        poneNumber.setPhoneNumber(2222222);
        Person person=new Person(null,22,"test","test",poneNumber,
                new Address("TEST","TEST",222222));
        Integer val = hibernateUtil.create(person,session);
        assertNotNull(val);
        hibernateUtil.delete(val,session);
        session.close();
    }

    @Test
    public void delete() {
        Session session2 = factory.openSession();
        PoneNumber poneNumber=new PoneNumber();
        poneNumber.setPhoneNumber(2222222);
        Person person=new Person(null,22,"test","test",poneNumber,
                new Address("TEST","TEST",222222));
        Transaction tx=null;
        Serializable id;
        try {
            tx = session2.beginTransaction();
            //do some work
            id = session2.save(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        hibernateUtil.delete((Integer) id, session2);

        assertNull(session2.get(Person.class,id));
    }

//    @Test
//    public void createWithId() {
//        try {
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/persondb_test?serverTimezone=UTC",
//                    "root",
//                    "root"
//            );
//            PreparedStatement preparedStatement= connection.prepareStatement(
//                    "insert into persondb_test.T_PErSON (P_ID, P_AGE, P_NAME, P_SURNAME) values (?, ?, ?, ?)"
//            );
//            hibernateUtil.createWithId(111,111,"test","test", preparedStatement);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        Session session = factory.openSession();
//        Person person = session.get(Person.class, 111);
//
//        assertNotNull(person);
//        hibernateUtil.delete(111,session);
//        session.close();
//    }

    @Test
    public void read() {
        Session session = factory.openSession();
        PoneNumber poneNumber=new PoneNumber();
        poneNumber.setPhoneNumber(2222222);
        Person person=new Person(null,22,"test","test",poneNumber,
                new Address("TEST","TEST",222222));
        Integer val = hibernateUtil.create(person,session);
        final Person read = hibernateUtil.read(val, session);
        assertEquals(val,read.getId());
        hibernateUtil.delete(val,session);
        assertNull(session.get(Person.class,val));
        session.close();
    }

    @Test
    public void update() {

        Session session = factory.openSession();
        PoneNumber poneNumber=new PoneNumber();
        poneNumber.setPhoneNumber(2222222);
        Person person=new Person(null,22,"test","test",poneNumber,
                new Address("TEST","TEST",222222));
        Integer val = hibernateUtil.create(person,session);
        PoneNumber poneNumber2 = new PoneNumber();
        poneNumber2.setPhoneNumber(11111111);
        Person personUPDATE=new Person(null,22,"test","test",
                poneNumber,
                new Address("TEST22","TEST22",111111));
        hibernateUtil.update(
                val,
                personUPDATE.getAge(),
                personUPDATE.getName(),
                personUPDATE.getSurname(),
                personUPDATE.getPoneNumber().getPhoneNumber(),
                personUPDATE.getAddress(),
                session);
        assertEquals(personUPDATE,session.get(Person.class,val));
        hibernateUtil.delete(val,session);
        assertNull(session.get(Person.class,val));
        session.close();
    }

}
