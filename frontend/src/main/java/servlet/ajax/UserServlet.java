package servlet.ajax;

import dao.AuthentificatonDAO;
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
public class UserServlet extends HttpServlet {
    @LogAnot
    Logger logger;

    AuthentificatonDAO auth;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String idlist = null;

        try {
            if (isRequestEmpty(request)) {
                idlist = auth.getAll();
            } else {
                idlist = auth.getUserFromEM(request.getParameter("id"));
            }
            logger.debug(idlist);
        } catch (SQLException e) {
            logger.error("SQLException {}", e);
            response.sendError(500, "SQL Exception.");
        }
        request.setAttribute("id", "{ \"items\": " + idlist + "}");
        logger.debug("{ \"items\": " + idlist + "}");

        response.setContentType("application/json");
        response.getWriter().print("{ \"items\": " + idlist + "}");

    }

    private boolean isRequestEmpty(HttpServletRequest request) {
        logger.debug("ID = {}", request.getParameter("id"));
        return request.getParameter("id") == null;
    }
}
