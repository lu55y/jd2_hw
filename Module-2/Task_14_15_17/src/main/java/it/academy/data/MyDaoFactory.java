package it.academy.data;

import javax.servlet.ServletConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDaoFactory {

    private static MyDaoFactory myDaoFactory;

    private MyDaoFactory() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Dao getDaoImpl(ServletConfig config) throws SQLException{
        Connection connection = DriverManager.getConnection(
                config.getServletContext().getInitParameter("database.url"),
                config.getServletContext().getInitParameter("database.username"),
                config.getServletContext().getInitParameter("database.password")
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
