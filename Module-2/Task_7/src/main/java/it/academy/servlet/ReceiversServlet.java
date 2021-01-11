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

@WebServlet(name = "Receivers", urlPatterns = "/receivers")
public class ReceiversServlet extends HttpServlet {

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
            List<Receiver>receivers;
            Dao receiverDao=myDaoFactory.getDaoImpl();
            if (num==null){
                receivers=receiverDao.getReceivers();
            }else {
                Receiver receiver=null;
                try {
                    receiver= receiverDao.getReceiver(Integer.parseInt(num));
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                receivers = receiver !=null ? List.of(receiver) : Collections.emptyList();

            }
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            for (Receiver receiver : receivers) {
                writer.println("num= "+receiver.getNum()+
                        "   name= "+receiver.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
