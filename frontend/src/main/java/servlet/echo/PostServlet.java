package servlet.echo;

import javax.inject.Singleton;
import javax.servlet.ServletException;
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
        String id = request.getParameter("userid");
        if (id == null) id = "NULL";
        response.sendRedirect(request.getContextPath() + "/echo/get?userid="+URLEncoder.encode(id, "UTF-8"));
    }
}
