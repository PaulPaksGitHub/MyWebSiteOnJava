package servlet.ajax;

import com.google.gson.Gson;
import dao.AuthentificatonDAO;
import org.apache.logging.log4j.Logger;
import servlet.ConnectionAnot;
import servlet.LogAnot;
import servlet.ProviderAnot;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@Singleton
public class UserServlet extends HttpServlet {
    @ProviderAnot
    Gson gson;

    @LogAnot
    Logger logger;

    @ConnectionAnot
    Connection conn;

    AuthentificatonDAO auth;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String idlist = null;

        try {
            if (isRequestEmpty(request)) {
                idlist = auth.getAll(conn);
            } else {
                //idlist = auth.getUserFromID(conn, request.getParameter("id"));
                idlist = auth.getUserFromEM(conn, request.getParameter("id"));
            }
            logger.debug(idlist);
        } catch (SQLException e) {
            logger.error("SQLException {}", e);
            response.sendError(500, "SQL Exception.");
        }
        request.setAttribute("id", idlist);


        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }

    private boolean isRequestEmpty(HttpServletRequest request) {
        logger.debug("ID = {}", request.getParameter("id"));
        return request.getParameter("id") == null;
    }
}
