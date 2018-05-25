package servlet;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

//@WebServlet(urlPatterns = "/echo/post")
@Singleton
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");
        if (id == null) id = "NULL";
        response.sendRedirect(request.getContextPath() + "/echo/get?id="+URLEncoder.encode(id, "UTF-8"));
    }
}
