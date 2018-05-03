package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;
import com.company.parameters.Parameters;
import com.company.parameters.ParseLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        checkDBconnection();

        ParseLine defaultParser = new ParseLine();

        logger.info("================================");

        if (args.length == 0) {
            defaultParser.parse(args);
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }

        logger.info("Get parameters: " + args);

        Parameters user = defaultParser.parse(args);

        Autorization autorization = new Autorization();
        Authentific authentific = new Authentific();
        Accounting accounting = new Accounting();

        if (user.canAutorize() && user.canAuthehtific() && user.canAccaunt()) {
            autorization.isAutorized(user);
            authentific.isAuthentificated(user);
            accounting.isAccounted(user);
        } else if (user.canAutorize() && user.canAuthehtific()) {
            autorization.isAutorized(user);
            authentific.isAuthentificated(user);
        } else if (user.canAutorize()) {
            autorization.isAutorized(user);
        }
        if (user.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
    }
    private static void checkDBconnection() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:file:./data/db", "sa", "");
        flyway.migrate();
    }
}
