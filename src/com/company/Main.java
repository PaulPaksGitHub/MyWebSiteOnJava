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

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String url = "jdbc:h2:file:./data/db";
    private static final String dbUser = "sa";
    private static final String dbPassword = "";

    public static void main(String[] args) throws SQLException {
        checkDBconnection();

        ParseLine defaultParser = new ParseLine();
        args = "-login pa -pass 12".split(" ");

        logger.info("================================");

        if (args.length == 0) {
            defaultParser.parse(args);
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }

        Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);

        logger.info("Get parameters: " + args.toString());

        Parameters user = defaultParser.parse(args);

        Autorization autorization = new Autorization();
        Authentific authentific = new Authentific();
        Accounting accounting = new Accounting();

        if (user.canAutorize() && user.canAuthehtific() && user.canAccaunt()) {
            autorization.isAutorized(user,conn);
            authentific.isAuthentificated(user,conn);
            accounting.isAccounted(user);
        } else if (user.canAutorize() && user.canAuthehtific()) {
            autorization.isAutorized(user,conn);
            authentific.isAuthentificated(user,conn);
        } else if (user.canAutorize()) {
            autorization.isAutorized(user,conn);
        }

        conn.close();

        if (user.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
    }
    private static void checkDBconnection() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, dbUser, dbPassword);
        flyway.migrate();
    }
}
