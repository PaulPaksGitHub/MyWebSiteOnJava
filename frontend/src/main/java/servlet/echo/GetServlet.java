package servlet.echo;

import org.apache.logging.log4j.Logger;
import servlet.LogAnot;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/echo/get")

@Singleton
public class GetServlet extends HttpServlet {
    @LogAnot
    Logger logger;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        logger.debug("Get servlet");
        String id = request.getParameter("userid");

        request.setAttribute("userid", id);
        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }
}
