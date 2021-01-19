package it.academy.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDaoFactory {

    private static MyDaoFactory myDaoFactory;

    private MyDaoFactory() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Dao getDaoImpl() throws SQLException{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/listexpenses?serverTimezone=UTC&createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "root"
        );
    return new DaoImpl(connection);
    }

    public static MyDaoFactory getInstance(DatabaseName databaseName) throws ClassNotFoundException{
        switch (databaseName){
            case MYSQL:{
                if (myDaoFactory==null){
                    myDaoFactory=new MyDaoFactory();
                }  return myDaoFactory;
            }
            case ORACLE:{
                return null;
            }
        }
        throw new IllegalArgumentException("Database name is unknown");
    }

}
