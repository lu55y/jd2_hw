package it.academy.servlet;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAgentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out= resp.getWriter();
        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        final Browser browser = userAgent.getBrowser();
        out.println("<html><head><title>First Servlet</title></head>");
        out.println("<body><h1>Приветствую пользователя"+browser+"</h1><br>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
