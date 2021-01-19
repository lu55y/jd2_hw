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

@WebServlet(value = "/receivers")
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
            Dao receiverDao=myDaoFactory.getDaoImpl(getServletConfig());
            List<Receiver> receivers = getReceivers(req, receiverDao);
            pageOut(resp, receivers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void pageOut(HttpServletResponse resp, List<Receiver> receivers) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Task 14</title></head>");
        writer.println("<body><h2>Receivers</h2>");
        for (Receiver receiver : receivers) {
            writer.println("<p>num= "+receiver.getNum()+
                    "   name= "+receiver.getName()+"</p>");
        }
        writer.println("<form action=\"/ListExpenses_14_15_17/Task14\" target=\"_self\">\n" +
                "   <button>Task 14</button>\n" +
                "</form></br>");
        writer.println("</body></html>");
    }

    private List<Receiver> getReceivers(HttpServletRequest req, Dao receiverDao) {
        String num = req.getParameter("num");
        List<Receiver>receivers;
        if (num==null){
            receivers= receiverDao.getReceivers();
        }else {
            Receiver receiver=null;
            try {
                receiver= receiverDao.getReceiver(Integer.parseInt(num));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            receivers = receiver !=null ? List.of(receiver) : Collections.emptyList();

        }
        return receivers;
    }
}
