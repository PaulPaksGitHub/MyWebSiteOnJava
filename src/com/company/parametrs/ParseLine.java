package com.company.parametrs;

import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ParseLine {
    private Options posixOptions = new Options();

    public Options getOptions() {
        return posixOptions;
    }

    public Parameters parse(String[] args) {
        Option optLogin = new Option("login", true, "Login");
        posixOptions.addOption(optLogin);

        Option optPass = new Option("pass", true, "Password");
        posixOptions.addOption(optPass);

        Option optRes = new Option("res", true, "Resource");
        posixOptions.addOption(optRes);

        Option optRole = new Option("role", true, "Role");
        posixOptions.addOption(optRole);

        Option optDs = new Option("ds", true, "Ds");
        posixOptions.addOption(optDs);

        Option optDe = new Option("de", true, "De");
        posixOptions.addOption(optDe);

        Option optVol = new Option("vol", true, "Volume");
        posixOptions.addOption(optVol);

        Option optHelp = new Option("help", false, "Help");
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
            if (commandLine.hasOption("login")) {
                log = commandLine.getOptionValues("login")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("pass")) {
                pas = commandLine.getOptionValues("pass")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("res")) {
                res = commandLine.getOptionValues("res")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("role")) {
                rol = commandLine.getOptionValues("role")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("ds")) {
                dts = commandLine.getOptionValues("ds")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("de")) {
                dte = commandLine.getOptionValues("de")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("vol")) {
                vol = commandLine.getOptionValues("vol")[0];
                hasParams = true;
            }
            if (commandLine.hasOption("h") || !hasParams) {
                h = true;
            }
            return new Parameters(log, pas, res, rol, dts, dte, vol, h);
        } catch (ParseException e) {
            return new Parameters("", "", "", "", "", "", "", true);
        }
    }

    public void printHelp(
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
