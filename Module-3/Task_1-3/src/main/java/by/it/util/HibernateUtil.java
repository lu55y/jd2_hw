package by.it.util;

import by.it.pojos.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HibernateUtil {

//    private static final EntityManagerFactory emFactory;
    private static SessionFactory sessionFactory;


//    static {
//        emFactory = Persistence.createEntityManagerFactory("by.it");
//    }
//
//    public static EntityManager getEntityManager() {
//        return emFactory.createEntityManager();
//    }
//
//    public static void close() {
//        emFactory.close();
//    }

    public HibernateUtil(){
        try {
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure("hibernate.cfg.xml")
                            .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        }catch (Throwable e){
            System.out.println("Initial SessionFactory creation failed"+e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession(){return sessionFactory.openSession(); }


    public Integer create(Integer age, String name, String surname){
        Transaction tx = null;
        final Session session = HibernateUtil.getSession();
        Person person=new Person(null,age,name,surname);
        Serializable id;
        try {
            tx = session.beginTransaction();
            id =session.save(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
        return (Integer) id;
    }
    public Integer createWithId(Integer id, Integer age, String name, String surname) throws SQLException {
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/persondb?serverTimezone=UTC",
                "root",
                "root"
        );
        Person person = new Person(id, age, name, surname);
        try ( PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into persondb.T_PErSON (P_ID, P_AGE, P_NAME, P_SURNAME)\n" +
                        " values (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setString(4, person.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(person);
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            throw e;
//        }
        return id;
    }

    public Person read(Integer id){
        final Session session = HibernateUtil.getSession();
        Person loadedPerson=session.get(Person.class,id);
        session.close();
        if (loadedPerson!=null) {
            return loadedPerson;
        }
        System.out.println("No such Person exists");
        return null;
    }

    public Person update(Integer id, Integer age, String name, String surname){
        final Session session = HibernateUtil.getSession();
        Person loadedPerson=read(id);
        if (loadedPerson!=null) {
            loadedPerson.setId(id);
            loadedPerson.setAge(age);
            loadedPerson.setName(name);
            loadedPerson.setSurname(surname);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(loadedPerson);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }
            return loadedPerson;
        }
        return null;
    }

    public boolean delete(Integer id){
        final Session session = HibernateUtil.getSession();
        Person loadedPerson=session.get(Person.class,id);
        Transaction tx=null;
        if (loadedPerson!=null){
            try {
                tx = session.beginTransaction();
                session.delete(loadedPerson);
                tx.commit();

            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }
            return true;
        }
        return false;
    }
}
