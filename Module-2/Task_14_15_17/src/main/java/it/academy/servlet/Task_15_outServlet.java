package it.academy.servlet;

import it.academy.data.Dao;
import it.academy.data.DatabaseName;
import it.academy.data.ListExpenses;
import it.academy.data.MyDaoFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/Task15")
public class Task_15_outServlet extends HttpServlet {

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
            Dao expenseDao=myDaoFactory.getDaoImpl(getServletConfig());
            List<ListExpenses> listExpenses=expenseDao.getListExpenses();
            req.setAttribute("listE",listExpenses);
            req.getRequestDispatcher("/jsp/listTask15.jsp").forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
