package it.academy.servlet;

import it.academy.data.*;

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

@WebServlet(value = "/Task14")
public class Task_14_outServlet extends HttpServlet {

    MyDaoFactory myDaoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final String databaseName =config.getServletContext().getInitParameter("database.name");
        try {
            myDaoFactory = MyDaoFactory.getInstance(DatabaseName.valueOf(databaseName));
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String num = req.getParameter("num");
            List<ListExpenses> expenses;
            Dao expenseDao=myDaoFactory.getDaoImpl(getServletConfig());
            if (num==null){
                expenses=expenseDao.getListExpenses();
            }else {
                ListExpenses expenseList=null;
                try {
                    expenseList= expenseDao.getListExpense(Integer.parseInt(num));
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                expenses = expenseList !=null ? List.of(expenseList) : Collections.emptyList();

            }
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();

            writer.println("<html><head><title>Task 14</title></head>");
            writer.println("<body><h2>List Expenses</h2>");
            for (ListExpenses expenseList : expenses) {
                writer.println("<p>num= "+expenseList.getNum()+
                        "   paydate= "+expenseList.getPaydate()+
                        "   receiver= "+expenseList.getReceiver()+
                        "   value= "+expenseList.getValue()+"</p>");
            }
            writer.println("<form action=\"/ListExpenses_14_15_17\" target=\"_self\">\n" +
                    "   <button>Start Page</button>\n" +
                    "</form></br>");
            writer.println("</body></html>");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
