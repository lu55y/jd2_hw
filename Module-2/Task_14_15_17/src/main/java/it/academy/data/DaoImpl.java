package it.academy.data;

import java.sql.*;
import java.util.ArrayList;

public class DaoImpl implements Dao{

    private final Connection connection;

    public DaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Receiver getReceiver(int num) {
        try (Statement statement=connection.createStatement();
             ResultSet resultSet= statement.executeQuery(
                     "select * from receivers where num="+num)){
            resultSet.next();
            Receiver receiver=new Receiver();
            receiver.setNum(resultSet.getInt("num"));
            receiver.setName(resultSet.getString("name"));
            return receiver;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver> receivers=new ArrayList<>();
        try (Statement statement=connection.createStatement();
             ResultSet resultSet= statement.executeQuery(
                     "select * from receivers")){
            while (resultSet.next()){
                Receiver receiver = new Receiver();
                receiver.setNum(resultSet.getInt("num"));
                receiver.setName(resultSet.getString("name"));
                receivers.add(receiver);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return receivers;
    }

    @Override
    public Expense getExpense(int num) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select * from expenses where num="+num)){
            resultSet.next();
            Expense expense=new Expense();
            expense.setNum(resultSet.getInt("num"));
            expense.setPaydate(resultSet.getString("paydate"));
            expense.setReceiver(resultSet.getInt("receiver"));
            expense.setValue(resultSet.getDouble("value"));
            return expense;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense>expenses=new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select * from expenses")){
            while (resultSet.next()){
                Expense expense=new Expense();
                expense.setNum(resultSet.getInt("num"));
                expense.setPaydate(resultSet.getString("paydate"));
                expense.setReceiver(resultSet.getInt("receiver"));
                expense.setValue(resultSet.getDouble("value"));
                expenses.add(expense);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenses;
    }

    @Override
    public int addReceiver(Receiver receiver) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into listexpenses.receivers (num , name)) values (?, ?)")){
            preparedStatement.setInt(1,receiver.getNum());
            preparedStatement.setString(2,receiver.getName());
            System.out.println("create:"+preparedStatement.executeUpdate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addExpense(Expense expense) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into listexpenses.receivers (num , name)) values (?, ?)")){
            preparedStatement.setInt(1,expense.getNum());
            preparedStatement.setString(2,expense.getPaydate());
            preparedStatement.setInt(3,expense.getReceiver());
            preparedStatement.setDouble(4,expense.getValue());
            preparedStatement.executeUpdate();
            System.out.println("create:"+preparedStatement.executeUpdate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ListExpenses> getListExpenses() {
        ArrayList<ListExpenses>listExpenses=new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select expenses.num,paydate,name,value from listexpenses.expenses,listexpenses.receivers\n" +
                             " where  receiver=receivers.num")){
            while (resultSet.next()){
                ListExpenses expenses=new ListExpenses();
                expenses.setNum(resultSet.getInt(1));
                expenses.setPaydate(resultSet.getString(2));
                expenses.setReceiver(resultSet.getString(3));
                expenses.setValue(resultSet.getDouble(4));
                listExpenses.add(expenses);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listExpenses;
    }

    @Override
    public ListExpenses getListExpense(int num) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select expenses.num,paydate,name,value from listexpenses.expenses,listexpenses.receivers\n" +
                             " where  receiver=receivers.num & expenses.num=" + num)) {
            resultSet.next();
            ListExpenses expenseL = new ListExpenses();
            expenseL.setNum(resultSet.getInt(1));
            expenseL.setPaydate(resultSet.getString(2));
            expenseL.setReceiver(resultSet.getString(3));
            expenseL.setValue(resultSet.getDouble(4));
            return expenseL;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
