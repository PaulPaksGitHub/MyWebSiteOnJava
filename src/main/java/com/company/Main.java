package main.java.com.company;

import main.java.com.company.accounting.Accounting;
import main.java.com.company.authorization.Authorization;
import main.java.com.company.authentification.Authentification;
import main.java.com.company.parameters.Parameters;
import main.java.com.company.parameters.ParseLine;
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

        if (param.canAutorize() && param.canAuthehtific() && param.canAccaunt()) {
            autorization.isAuthorizable(param, conn);
            authentific.isAuthentificable(param, conn);
            checkExit(accounting.isAccountable(param, conn));
        } else if (param.canAutorize() && param.canAuthehtific()) {
            autorization.isAuthorizable(param, conn);
            authentific.isAuthentificable(param, conn);
        } else if (param.canAutorize()) {
            autorization.isAuthorizable(param, conn);
        }
        if (param.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
        conn.close();
    }

    private static void checkDBconnection() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, dbUser, dbPassword);
        flyway.migrate();
    }

    private static void checkExit(SysExits exit) throws SQLException {
        if (exit.equals(SysExits.valueOf("EXIT0"))) {
            System.out.println("ALL IS OK");
        }
        if (!exit.equals(SysExits.valueOf("EXIT0"))) {
            conn.close();
        }
        if (exit.equals(SysExits.valueOf("EXIT1"))) {
            System.exit(1);
        } else if (exit.equals(SysExits.valueOf("EXIT2"))) {
            System.exit(2);
        } else if (exit.equals(SysExits.valueOf("EXIT3"))) {
            System.exit(3);
        } else if (exit.equals(SysExits.valueOf("EXIT4"))) {
            System.exit(4);
        } else if (exit.equals(SysExits.valueOf("EXIT5"))) {
            System.exit(5);
        } else if (exit.equals(SysExits.valueOf("EXIT6"))) {
            System.exit(6);
        }
    }
}
