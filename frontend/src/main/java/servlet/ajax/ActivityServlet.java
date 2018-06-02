package servlet.ajax;

import com.google.gson.Gson;
import dao.AccountingDAO;
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
public class ActivityServlet extends HttpServlet {
    @LogAnot
    Logger logger;

    @ConnectionAnot
    Connection conn;

    @ProviderAnot
    Gson gson;

    AccountingDAO dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String text;

            if (isRequestEmpty(request)) {
                text = dao.getAll(conn);
            } else if (hasId(request)) {
                text = dao.getAccFromID(conn, request.getParameter("id"));
            } else {
                text = dao.getAccFromAutorityID(conn, request.getParameter("autorityid"));
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
        return (request.getParameter("id") == null && request.getParameter("autorityid") == null);
    }

    private boolean hasId(HttpServletRequest request) {
        return request.getParameter("id") != null;
    }
}

