package com.company;

import com.company.accounting.Accounting;
import com.company.authentification.Authentification;
import com.company.authorization.Authorization;
import com.company.parameters.Parameters;
import com.company.parameters.ParseLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ActivityPost {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public String getActivity(Parameters param, Connection conn) throws SQLException {
        ParseLine defaultParser = new ParseLine();

        //Parameters param = defaultParser.parse(params.split(" "));

        Authorization autorization = new Authorization();
        Authentification authentific = new Authentification();
        Accounting accounting = new Accounting();
        int success = 0;

        if (param.canAuthentific() && param.canAuthorize() && param.canAccaunt()) {
            success+=checkExit(authentific.isAuthentificable(param, conn));
            success+=checkExit(autorization.isAuthorizable(param, conn));
            success+=checkExit(accounting.isAccountable(param, conn));
        } else if (param.canAuthentific() && param.canAuthorize()) {
            success+=checkExit(authentific.isAuthentificable(param, conn));
            success+=checkExit(autorization.isAuthorizable(param, conn));
        } else if (param.canAuthentific()) {
            success+=checkExit(authentific.isAuthentificable(param, conn));
        }
        if (param.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
        if (success == 0) {
            return "Seccesful";
        } else {
            return "Wrong operation";
        }
    }

    private static int checkExit(SysExits exit) throws SQLException {
        if (exit.equals(SysExits.EXIT0)) {
            logger.info("Operation complete");
        }
        else {
            logger.error("Operation crashed");
            return exit.getExitCode();
        }
        return 0;
    }
}
