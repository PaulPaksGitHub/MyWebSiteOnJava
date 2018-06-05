package servlet.ajax;

import com.company.ActivityPost;
import com.company.parameters.Parameters;
import dao.AccountingDAO;
import org.apache.logging.log4j.Logger;
import servlet.LogAnot;

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

    AccountingDAO dao;

    Connection conn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String text = null;
        try {
            if (isRequestEmpty(request)) {
                text = dao.getAll();
            } else if (hasId(request)) {
                text = dao.getAccFromID(request.getParameter("id"));
            } else {
                text = dao.getAccFromAutorityID(request.getParameter("autorityid"));
            }
            logger.debug(text);
            request.setAttribute("id", text);
        } catch (SQLException e) {
            logger.error("SQLException {}", e);
            response.sendError(500, "SQLException");
        }
        //request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
        response.setContentType("application/json");
        response.getWriter().print("{ \"items\": " + text + "}");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Parameters params = new Parameters();

        params.setLogin(getParameter(request, "login"));
        params.setPass(getParameter(request, "pass"));
        params.setRes(getParameter(request, "res"));
        params.setRole(getParameter(request, "role"));
        params.setDs(getParameter(request, "ds"));
        params.setDe(getParameter(request, "de"));
        params.setVol(getParameter(request, "vol"));

        ActivityPost post = new ActivityPost();
        String str = null;
        logger.debug("{} {}", params.getLogin(), params.getPass());
        try {
            str = post.getActivity(params, conn);
        } catch (SQLException e) {
            logger.error("Exception in parse form");
            response.sendError(500, "Backend error");
        }
        request.setAttribute("id", str);
        //request.getRequestDispatcher("/getservlet.jsp").forward(request, response);
        response.setContentType("application/json");
        response.getWriter().print(str);
    }

    private boolean isRequestEmpty(HttpServletRequest request) {
        return (request.getParameter("id") == null && request.getParameter("autorityid") == null);
    }

    private boolean hasId(HttpServletRequest request) {
        return request.getParameter("id") != null;
    }

    private String getParameter(HttpServletRequest request, String name) {
        logger.debug(request.getParameter(name));
        if (request.getParameter(name) == "") {
            return null;
        }
        return request.getParameter(name);
    }
}

