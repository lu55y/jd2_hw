package it.academy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CounterServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String realPath = req.getServletContext().getRealPath("/counter.txt");
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(realPath));
            final String s = reader.readLine();
            int counter = 1 + Integer.parseInt(s);
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>First Servlet</title></head>");
            out.println("<body><h1>Count page</h1>");
            out.println("<body><h2> </h2>");
            out.println("<body><h3>Counter: " + counter + "</h3>");
            out.println("</body></html>");
            final BufferedWriter writer = new BufferedWriter(new FileWriter(realPath));
            writer.write(Integer.toString(counter));
            writer.flush();
            reader.close();
            writer.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
