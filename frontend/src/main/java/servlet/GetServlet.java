package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/echo/get")
public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");
        //response.getWriter().print("GET id = " + id);
        request.setAttribute("id", id);
        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }
}
