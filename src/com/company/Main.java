package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;
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

    public static void main(String[] args) throws SQLException {
        checkDBconnection();
        ParseLine defaultParser = new ParseLine();

        logger.info("================================");

        if (args.length == 0) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }
        args = args[0].replace("'", "").split(" ");

        Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);

        logger.info("Get parameters: " + Arrays.toString(args));

        Parameters param = defaultParser.parse(args);

        Autorization autorization = new Autorization();
        Authentific authentific = new Authentific();
        Accounting accounting = new Accounting();

        if (param.canAutorize() && param.canAuthehtific() && param.canAccaunt()) {
            autorization.isAutorized(param, conn);
            authentific.isAuthentificated(param, conn);
            accounting.isAccounted(param, conn);
        } else if (param.canAutorize() && param.canAuthehtific()) {
            autorization.isAutorized(param, conn);
            authentific.isAuthentificated(param, conn);
        } else if (param.canAutorize()) {
            autorization.isAutorized(param, conn);
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
}
