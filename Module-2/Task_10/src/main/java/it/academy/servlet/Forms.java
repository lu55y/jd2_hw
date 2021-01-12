package it.academy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("task10")
public class Forms extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("pnumber");
        String email = req.getParameter("email");

        pageOut(out, name, phoneNumber, email);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void pageOut(PrintWriter out, String name, String phoneNumber, String email) {
        out.println("<html lang=\"en\"><head><title>Form</title></head>");
        out.println("<body>");
        if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
            out.println("<h1>This is your contact</h1>");
            out.println("<p>Name:" + name + "<br>");
            out.println("Pone number:" + phoneNumber + "<br>");
            out.println("Email:" + email + "</p><br>");
        }else
        if (name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
            out.println("<h1 style=\"color:red;\">Name not entered, Please enter your name!</h1>");
        }else
        if (!name.isEmpty() && (phoneNumber.isEmpty() && email.isEmpty())) {
            out.println("<h1 style=\"color:red;\">Phone number and email are not entered, Please enter!</h1>");
        }
        else {
            out.println("<h1 style=\"color:red;\">The form is not completed or filled out incorrectly,Please try again!</h1>");
        }
        out.println("</body></html>");
    }
}

