package org.gradle.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/echo/*")
public class EchoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Check(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Check(request, response);
    }

    private void Check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        if (!request.getRequestURI().equals("/prj/echo/post") &&
                !request.getRequestURI().equals("/prj/echo/get")) {
            response.getWriter().println("<h2>ERROR 404</h2>");
            response.getWriter().println("<h2>Try to connect URL:"+request.getRequestURI()+"</h2>");
        }
    }
}


