package servlet.ajax;

import com.company.authentification.AuthentificatonDAO;
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
public class UserServlet extends HttpServlet {
    @ProviderAnot
    Gson gson;

    @LogAnot
    Logger logger;

    @ConnectionAnot
    Connection conn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        logger.error("ID = " + id);
        AuthentificatonDAO auth = new AuthentificatonDAO();

        String idlist = null;

        if (id == null) {
            try {
                idlist = auth.getAll(conn);
                logger.error(idlist);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("id", "ID of all users:" + idlist);
        } else {
            try {
                idlist = auth.getUserFromID(conn, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("id", "User json:" + idlist);
        }

        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }
}
