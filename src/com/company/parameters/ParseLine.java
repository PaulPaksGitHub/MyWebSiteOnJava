package com.company.parameters;

import com.company.parameters.Parameters;
import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ParseLine {
    private Options posixOptions = new Options();

    public Options getOptions() {
        return posixOptions;
    }

    public Parameters parse(String[] args) {
        Parameters.Builder params = new Parameters().newBuilder();
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
        try {
            CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);
            if (commandLine.hasOption("login")) {
                params.setLogin(commandLine.getOptionValues("login")[0]);
            }
            if (commandLine.hasOption("pass")) {
                params.setPass(commandLine.getOptionValues("pass")[0]);
            }
            if (commandLine.hasOption("res")) {
                params.setRes(commandLine.getOptionValues("res")[0]);
            }
            if (commandLine.hasOption("role")) {
                params.setRole(commandLine.getOptionValues("role")[0]);
            }
            if (commandLine.hasOption("ds")) {
                params.setDs(commandLine.getOptionValues("ds")[0]);
            }
            if (commandLine.hasOption("de")) {
                params.setDe(commandLine.getOptionValues("de")[0]);
            }
            if (commandLine.hasOption("vol")) {
                params.setVol(commandLine.getOptionValues("vol")[0]);
            }
            if (commandLine.hasOption("h") || params.build().isEmpty()) {
                params.setH(true);
            }
            return params.build();
        } catch (ParseException e) {
            params.setH(true);
            return params.build();
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
