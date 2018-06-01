package servlet.ajax;

import com.company.authorization.AuthorizationDAO;
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
public class AuthorityServlet extends HttpServlet {
    @LogAnot
    Logger logger;

    @ConnectionAnot
    Connection conn;

    @ProviderAnot
    Gson gson;

    AuthorizationDAO dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("userid");
        String userid = request.getParameter("userid");
        String text;

        request.setAttribute("userid", "AMA AUTHORITY SERVLET");



        if (id == null && userid == null) {
            try {
                text = dao.getAll(conn);
                logger.error(text);
                request.setAttribute("userid", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (id != null) {
            try {
                text = dao.getResFromID(conn, id);
                logger.error(text);
                request.setAttribute("userid", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                text = dao.getResFromUserID(conn, userid);
                logger.error(text);
                request.setAttribute("userid", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
    }
}
