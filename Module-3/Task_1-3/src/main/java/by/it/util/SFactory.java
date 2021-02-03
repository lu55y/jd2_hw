package by.it.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SFactory {

    public SFactory() {

    }


    public static Session getOpenSession(){
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        return sessionFactory.openSession();
    }

    public static PreparedStatement getOpenConnection() {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/persondb?serverTimezone=UTC",
                    "root",
                    "root"
            );
            preparedStatement = connection.prepareStatement(
                    "insert into persondb.T_PErSON (P_ID, P_AGE, P_NAME, P_SURNAME) values (?, ?, ?, ?)");
        } catch (SQLException throwables) {
            System.out.println("CONNECTION ERROR"+throwables);
            throwables.printStackTrace();
        }
        return preparedStatement;
    }
}
