package it.academy.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CounterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String realPath = req.getServletContext().getRealPath("/counter.txt");

        try {
            final String string = getString(req, resp, realPath);

            jpegOut(resp, string);

//            outPage(resp, string);
            write(string,realPath);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void write(String string, String realPath) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(realPath));
        writer.write(string);
        writer.flush();
        writer.close();
    }

    private String getString(HttpServletRequest req, HttpServletResponse resp, String realPath) throws IOException {

        final BufferedReader reader = new BufferedReader(new FileReader(realPath));
        final String s = reader.readLine();
        reader.close();
        boolean uniqId = getUniqId(req, resp);

        if (uniqId){
            int counter = 1 + Integer.parseInt(s);
            String string = Integer.toString(counter);
            return string;
        }
        return s;
    }

    private boolean getUniqId(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Cookie".equals(cookie.getName())) {
                    return false;
                }
            }
        }else {
            Cookie newCookieUser = new Cookie("Cookie", "this is unique user");
            newCookieUser.setMaxAge(60*60*24);
            resp.addCookie(newCookieUser);
        }
        return true;
    }

    private void outPage(HttpServletResponse resp, String counter) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>First Servlet</title></head>");
        out.println("<body><h1>Count page</h1>");
        out.println("<body><h2> </h2>");
        out.println("<body><h3>New users per day: " + counter + "</h3>");
        out.println("</body></html>");
    }

    private void jpegOut(HttpServletResponse resp, String string) throws IOException {
        resp.setContentType("image/jpeg");

        BufferedImage image = new BufferedImage(1000, 450, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 48));
        graphics.setColor(Color.ORANGE);
        graphics.drawString("New users per day:" + string, 300, 300);
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
