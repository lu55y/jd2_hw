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
            Dao expenseDao=myDaoFactory.getDaoImpl(getServletConfig());
            List<Expense> expenses = getExpenses(req, expenseDao);
            pageOut(resp, expenses);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void pageOut(HttpServletResponse resp, List<Expense> expenses) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Task 14</title></head>");
        writer.println("<body><h2>Expenses</h2>");
        for (Expense expens : expenses) {
            writer.println("<p>num= "+expens.getNum()+
                    "   paydate= "+expens.getPaydate()+
                    "   receiver= "+expens.getReceiver()+
                    "   value= "+expens.getValue()+"</p>");
        }
        writer.println("<form action=\"/ListExpenses_14_15_17/Task14\" target=\"_self\">\n" +
                "   <button>Task 14</button>\n" +
                "</form></br>");
        writer.println("</body></html>");
    }

    private List<Expense> getExpenses(HttpServletRequest req, Dao expenseDao) {
        String num = req.getParameter("num");
        List<Expense>expenses;
        if (num==null){
            expenses= expenseDao.getExpenses();
        }else {
            Expense expense=null;
            try {
                expense= expenseDao.getExpense(Integer.parseInt(num));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            expenses = expense !=null ? List.of(expense) : Collections.emptyList();

        }
        return expenses;
    }
}
