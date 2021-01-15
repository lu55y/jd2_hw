package it.academy.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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

        String fileCounts = reedFile(realPath);

        boolean uniqueUser = getUniqId(req, resp);

        String string = getStringCount(fileCounts, uniqueUser);

        jpegOut(resp, string);

//            outPage(resp, string);

        writeToFile(string, realPath);

    }

    private String getStringCount(String fileCounts, boolean uniqueUser) {
        int count = Integer.parseInt(fileCounts);
        if (uniqueUser) {
            count = count + 1;
        }
        return Integer.toString(count);
    }

    private String reedFile(String realPath) {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(realPath))) {
            line = reader.readLine();
            if (line==null)line="0";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private void writeToFile(String string, String realPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(realPath))){
        writer.write(string);
        }
    }

    private boolean getUniqId(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("NewUser".equals(cookie.getName())) {
                    return false;
                }
            }
        }
        addCookieUser(resp);
        return true;
    }

    private void addCookieUser(HttpServletResponse resp) {
        Cookie newCookieUser = new Cookie("NewUser", "1");
        newCookieUser.setMaxAge(60 * 60 * 24);
        resp.addCookie(newCookieUser);
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
