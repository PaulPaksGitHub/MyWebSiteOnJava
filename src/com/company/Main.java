package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;
import com.company.flyway_migrations.FlywayCheck;
import com.company.parameters.Parameters;
import com.company.line_parser.ParseLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger("Main.class");

    public static void main(String[] args) throws SQLException {
        FlywayCheck migrate = new FlywayCheck();
        migrate.checkDB();

        ParseLine defaultParser = new ParseLine();

        logger.info("================================");

        if (args.length == 0) {
            defaultParser.parse("".split(" "));
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }

        logger.info("Get parameters: " + args[0].replaceAll("'", ""));

        Parameters user = defaultParser.parse(args[0].replaceAll("'", "").split(" "));

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
}
