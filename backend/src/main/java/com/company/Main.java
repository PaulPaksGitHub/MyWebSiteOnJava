package com.company;

import com.company.accounting.Accounting;
import com.company.authentification.Authentification;
import com.company.authorization.Authorization;
import com.company.parameters.Parameters;
import com.company.parameters.ParseLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";
    private static Connection conn = null;

    public static void main(String[] args) throws SQLException {
        logger.info("================================");
        logger.info("Start migrations");
        checkDBconnection();
        logger.info("End migrations");

        ParseLine defaultParser = new ParseLine();

        if (args.length == 0) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }

        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        logger.info("Get parameters: {}", Arrays.toString(args));

        Parameters param = defaultParser.parse(args);

        Authorization autorization = new Authorization();
        Authentification authentific = new Authentification();
        Accounting accounting = new Accounting();

        if (param.canAuthentific() && param.canAuthorize() && param.canAccaunt()) {
            checkExit(authentific.isAuthentificable(param, conn));
            checkExit(autorization.isAuthorizable(param, conn));
            checkExit(accounting.isAccountable(param, conn));
        } else if (param.canAuthentific() && param.canAuthorize()) {
            checkExit(authentific.isAuthentificable(param, conn));
            checkExit(autorization.isAuthorizable(param, conn));
        } else if (param.canAuthentific()) {
            checkExit(authentific.isAuthentificable(param, conn));
        }
        if (param.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
        conn.close();
    }

    public static void checkDBconnection() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, dbUser, dbPassword);
        flyway.migrate();
    }

    private static void checkExit(SysExits exit) throws SQLException {
        if (exit.equals(SysExits.EXIT0)) {
            logger.info("Operation complete");
        }
        else {
            logger.error("Operation crashed");
            conn.close();
            System.exit(exit.getExitCode());
        }
    }
}
