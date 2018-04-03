package com.company;

import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ParseLine {
    private Options posixOptions = new Options();

    public Options getOptions() {
        return posixOptions;
    }

    public Parametrs parse(String[] args) {
        Option optLogin = new Option("l", "login", true, "Login");
        posixOptions.addOption(optLogin);

        Option optPass = new Option("p", "pass", true, "Password");
        posixOptions.addOption(optPass);

        Option optRes = new Option("r", "res", true, "Resource");
        posixOptions.addOption(optRes);

        Option optRole = new Option("o", "role", true, "Role");
        posixOptions.addOption(optRole);

        Option optDs = new Option("s", "ds", true, "Ds");
        posixOptions.addOption(optDs);

        Option optDe = new Option("e", "de", true, "De");
        posixOptions.addOption(optDe);

        Option optVol = new Option("v", "vol", true, "Volume");
        posixOptions.addOption(optVol);

        Option optHelp = new Option("h", "help", false, "Help");
        posixOptions.addOption(optHelp);

        CommandLineParser cmdLinePosixParser = new DefaultParser();
        String log = "";
        String pas = "";
        String res = "";
        String rol = "";
        String dts = "";
        String dte = "";
        String vol = "";
        boolean h = false;
        try {
            CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);
            boolean hasParams = false;
            if (commandLine.hasOption("l")) {
                log = commandLine.getOptionValues("l")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("p")) {
                pas = commandLine.getOptionValues("p")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("r")) {
                res = commandLine.getOptionValues("r")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("o")) {
                rol = commandLine.getOptionValues("o")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("s")) {
                dts = commandLine.getOptionValues("s")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("e")) {
                dte = commandLine.getOptionValues("e")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("v")) {
                vol = commandLine.getOptionValues("v")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("h") || !hasParams) {
                h = true;
            }
            return new Parametrs(log, pas, res, rol, dts, dte, vol, h);
        } catch (ParseException e) {
            return new Parametrs("", "", "", "", "", "", "", true);
        }
    }

    public static void printHelp(
            final Options options,
            final OutputStream out) {
        final int printedRowWidth = 80;
        final String header = "Options";
        final String footer = "-- HELP --";
        final int spacesBeforeOption = 3;
        final int spacesBeforeOptionDescription = 5;
        final boolean displayUsage = true;
        final String commandLineSyntax = "java test.jar";
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.flush();
    }
}
