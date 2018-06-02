package servlet.ajax;

import dao.AuthorizationDAO;
import org.apache.logging.log4j.Logger;
import servlet.LogAnot;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class AuthorityServlet extends HttpServlet {
    @LogAnot
    Logger logger;

    AuthorizationDAO dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            String text;
            if (isRequestEmpty(request)) {
                text = dao.getAll();
            } else if (hasId(request)) {
                text = dao.getResFromID(request.getParameter("id"));
            } else {
                text = dao.getResFromUserID(request.getParameter("userid"));
            }
            logger.debug(text);
            request.setAttribute("id", text);
        } catch (SQLException e) {
            logger.error("SQLException {}", e);
            response.sendError(500, "SQLException");
        }

        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }

    private boolean isRequestEmpty(HttpServletRequest request) {
        return (request.getParameter("id") == null && request.getParameter("userid") == null);
    }

    private boolean hasId(HttpServletRequest request) {
        return request.getParameter("id") != null;
    }
}

