package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;
import com.company.parametrs.Parameters;
import com.company.parametrs.ParseLine;
import org.apache.log4j.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        FlywayCheck migrate = new FlywayCheck();
        migrate.checkDB();

        ParseLine defaultParser = new ParseLine();
        if (args.length == 0) {
            defaultParser.parse("".split(" "));
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
            System.exit(0);
        }

        logger.info("Parametrs: " + args[0].replaceAll("'", ""));

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
