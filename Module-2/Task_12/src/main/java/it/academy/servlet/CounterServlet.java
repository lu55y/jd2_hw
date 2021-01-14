package it.academy.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
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
        resp.setContentType("image/jpeg");
        final String realPath = req.getServletContext().getRealPath("/counter.txt");
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(realPath));
            final String s = reader.readLine();
            int counter = 1 + Integer.parseInt(s);
            final String string = Integer.toString(counter);

            jpegOut(resp, string);

//            outPage(resp, counter);

            final BufferedWriter writer = new BufferedWriter(new FileWriter(realPath));
            writer.write(string);
            writer.flush();
            reader.close();
            writer.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void outPage(HttpServletResponse resp, int counter) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>First Servlet</title></head>");
        out.println("<body><h1>Count page</h1>");
        out.println("<body><h2> </h2>");
        out.println("<body><h3>Counter: " + counter + "</h3>");
        out.println("</body></html>");
    }

    private void jpegOut(HttpServletResponse resp, String string) throws IOException {
        BufferedImage image = new BufferedImage(500,200,BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC,48));
        graphics.setColor(Color.ORANGE);
        graphics.drawString(string,150,150);
        ServletOutputStream outputStream= resp.getOutputStream();
        ImageIO.write(image,"jpeg",outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
