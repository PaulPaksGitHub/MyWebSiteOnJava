package servlet.ajax;

import com.company.accounting.AccountingDAO;
import com.google.gson.Gson;
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
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        logger.debug("id = " + id);
        String autorityid = request.getParameter("autorityid");
        logger.debug("autorityid = " + autorityid);
        String text;
        request.setAttribute("id", "AMA Activity SERVLET");

        if (id == null && autorityid == null) {
            try {
                text = dao.getAll(conn);
                logger.error(text);
                request.setAttribute("id", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (id != null) {
            try {
                text = dao.getAccFromID(conn, id);
                logger.error(text);
                request.setAttribute("id", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                text = dao.getAccFromAutorityID(conn, autorityid);
                logger.error(text);
                request.setAttribute("id", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }
}

