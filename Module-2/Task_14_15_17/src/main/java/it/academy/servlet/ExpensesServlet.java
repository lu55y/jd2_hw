package it.academy.servlet;


import it.academy.data.Dao;
import it.academy.data.DatabaseName;
import it.academy.data.Expense;
import it.academy.data.MyDaoFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(value = "/expenses")
public class ExpensesServlet extends HttpServlet {

    MyDaoFactory myDaoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final String databaseName =config.getServletContext().getInitParameter("database.name");
        try {
            myDaoFactory =MyDaoFactory.getInstance(DatabaseName.valueOf(databaseName));
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String num = req.getParameter("num");
            List<Expense>expenses;
            Dao expenseDao=myDaoFactory.getDaoImpl(getServletConfig());
            if (num==null){
                expenses=expenseDao.getExpenses();
            }else {
                Expense expense=null;
                try {
                    expense= expenseDao.getExpense(Integer.parseInt(num));
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                expenses = expense !=null ? List.of(expense) : Collections.emptyList();

            }
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            for (Expense expens : expenses) {
                writer.println("num= "+expens.getNum()+
                        "   paydate= "+expens.getPaydate()+
                        "   receiver= "+expens.getReceiver()+
                        "   value= "+expens.getValue());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
