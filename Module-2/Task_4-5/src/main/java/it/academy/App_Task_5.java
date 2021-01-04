package it.academy;

import java.sql.*;


//D:\Java\jdk-11\bin\java.exe -classpath D:\home-work\Module-2\Task_4\target\classes;D:\Java\.m2\repository\mysql\mysql-connector-java\8.0.22\mysql-connector-java-8.0.22.jar it.academy.App_Task_5
class App_Task_5 {
    final static private String DBURL = "jdbc:mysql://localhost:3306/listexpenses?serverTimezone=UTC";


    public static void main(String[] args) {
/*
        StringBuffer stringBuffer = new StringBuffer();
        String s = ", ";
        for (int i = 0; i <= 3; i++) {
            if (i == 1) {
                stringBuffer.append("'").append(args[i]).append("'").append(s);
            } else if (i == 3) {
                s = " ";
                stringBuffer.append(args[i]).append(s);
            } else
                stringBuffer.append(args[i]).append(s);
        }
        "app();"

        getArgs(stringBuffer);
 */
        getArgs(args);
    }

    private static void getArgs(String[] line) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConnection = DriverManager.getConnection(DBURL, "root", "root");
            PreparedStatement preparedStatement;
            preparedStatement = myConnection.prepareStatement(
                    "insert into expenses (num,paydate,receiver,value) value (?,?,?,?);");
            preparedStatement.setInt(1, Integer.parseInt(line[0]));
            preparedStatement.setDate(2, Date.valueOf(line[1]));
            preparedStatement.setInt(3, Integer.parseInt(line[2]));
            preparedStatement.setDouble(4, Double.parseDouble(line[3]));
            printTablet(preparedStatement);
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("" +
                    "Введите корректные данные через пробел где:\n" +
                    "1      номер операнции(Int)\n" +
                    "2      дата YYYY-MM-DD(Date)\n" +
                    "3      номер получателя(Int)\n" +
                    "4      сумма(Double)\n");
        }
    }

    private static void printTablet(PreparedStatement preparedStatement) throws SQLException {
        String query = "select expenses.num,paydate,receiver,value from listexpenses.expenses";
        ResultSet resultSet = preparedStatement.executeQuery(query);
        System.out.println();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4));
        }
    }


/*
    private static void app() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(DBURL, "root", "root");
            Statement statement = myConnection.createStatement();
            String query = "select expenses.num,paydate,name,value from listexpenses.expenses,listexpenses.receivers\n" +
                    " where  receiver=receivers.num";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getString(4));
            }
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/


}
