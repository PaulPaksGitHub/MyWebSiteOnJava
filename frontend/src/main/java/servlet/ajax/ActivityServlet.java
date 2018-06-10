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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        response.setContentType("application/json");
        response.getWriter().print("{ \"items\": " + text + "}");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
				
		GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        Parameters params = new Parameters();
		params = gson.fromJson(printFormVars(request),Parameters.class);
		checkOnEmptyParam(params);
		logger.debug("Deseriavized parameters: {}", gson.toJson(params));

        ActivityPost post = new ActivityPost();
        String str = null;

        try {
            str = post.getActivity(params, conn);
        } catch (SQLException e) {
            logger.error("Exception in parse form");
            response.sendError(500, "Backend error");
        }
        logger.debug("str = {}", str);
        response.setContentType("application/json");
        response.getWriter().print("{ \"items\": \"" + str + "\"}");
    }

    private boolean isRequestEmpty(HttpServletRequest request) {
        return (request.getParameter("id") == null && request.getParameter("autorityid") == null);
    }

    private boolean hasId(HttpServletRequest request) {
        return request.getParameter("id") != null;
    }

//    private String getParameter(HttpServletRequest request, String name) {
//        logger.debug("{} = {}", name, request.getParameter(name));
//        if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
//            return null;
//        }
//        return request.getParameter(name);
//    }

    private void checkOnEmptyParam(Parameters params) {
        if (params.getLogin().equals("")) {
            params.setLogin(null);
        }
        if (params.getPass().equals("")) {
            params.setPass(null);
        }
		if (params.getRes().equals("")) {
            params.setRes(null);
        }
		if (params.getRole().equals("")) {
            params.setRole(null);
        }
		if (params.getDs().equals("")) {
            params.setDs(null);
        }
		if (params.getDe().equals("")) {
            params.setDe(null);
        }
		if (params.getVol().equals("")) {
            params.setVol(null);
        }
    }

    private String printFormVars(HttpServletRequest request) {
        java.util.Enumeration enu = request.getParameterNames();
		String par = null;
        while (enu.hasMoreElements()) {
            String paramName = (String) enu.nextElement();
            logger.debug("PARAM: "
                    + paramName
                    + ": "
                    + request.getParameter(paramName));
			par = paramName;
        }
		return par;
    }
}

