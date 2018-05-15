package com.company.parameters;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ParseLine {
    private static final Logger logger = LogManager.getLogger(ParseLine.class);
    private Options posixOptions = new Options();

    public Options getOptions() {
        return posixOptions;
    }

    public Parameters parse(String[] args) {
        Parameters params = new Parameters();
        CommandLineParser cmdLinePosixParser = new DefaultParser();
        try {
            CommandLine commandLine = cmdLinePosixParser.parse(getOptions(), args);
            params.setLogin(commandLine.getOptionValue("login",null));

            params.setPass(commandLine.getOptionValue("pass", null));

            params.setRes(commandLine.getOptionValue("res",null));

            params.setRole(commandLine.getOptionValue("role", null));

            params.setDs(commandLine.getOptionValue("ds",null));

            params.setDe(commandLine.getOptionValue("de", null));

            params.setVol(commandLine.getOptionValue("vol", null));

            if (commandLine.hasOption("h") || params.isParamEmpty()) {
                params.setH(true);
            }
            return params;
        } catch (ParseException e) {
            logger.error(e);
            params.setH(true);
            return params;
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

    public ParseLine() {
        posixOptions.addOption(new Option("login", true, "Login"));
        posixOptions.addOption(new Option("pass", true, "Password"));
        posixOptions.addOption(new Option("res", true, "Resource"));
        posixOptions.addOption(new Option("role", true, "Role"));
        posixOptions.addOption(new Option("ds", true, "Ds"));
        posixOptions.addOption(new Option("de", true, "De"));
        posixOptions.addOption(new Option("vol", true, "Volume"));
        posixOptions.addOption(new Option("help", false, "Help"));
    }
}
