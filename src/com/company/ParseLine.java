package com.company;
import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ParseLine {
    public static Parametrs parse(String[] args) {
        Options posixOptions = new Options();

        Option optLogin = new Option("l", "login", true, "Login");
        //login.setArgs(1);
        //login.setOptionalArg(false);
        //login.setArgName("login ");
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
        String log="",pas="",res="",rol="",dts="",dte="",vol="";
        Parametrs start = new Parametrs(log,pas,res,rol,dts,dte,vol);
        try {
            CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);
            if (commandLine.hasOption("l")) {
                String[] arguments = commandLine.getOptionValues("l");
                System.out.println("We try to Login with: " + arguments[0]);
                log=arguments[0];
            }
            if (commandLine.hasOption("p")) {
                String[] arguments = commandLine.getOptionValues("p");
                System.out.println("We try to Pass with: " + arguments[0]);
                pas=arguments[0];
            }
            if (commandLine.hasOption("r")) {
                String[] arguments = commandLine.getOptionValues("r");
                System.out.println("We try to Pass with: " + arguments[0]);
                res=arguments[0];
            }
            if (commandLine.hasOption("o")) {
                String[] arguments = commandLine.getOptionValues("o");
                System.out.println("We try to Pass with: " + arguments[0]);
                rol=arguments[0];
            }
            if (commandLine.hasOption("s")) {
                String[] arguments = commandLine.getOptionValues("s");
                System.out.println("We try to Pass with: " + arguments[0]);
                dts=arguments[0];
            }
            if (commandLine.hasOption("e")) {
                String[] arguments = commandLine.getOptionValues("e");
                System.out.println("We try to Pass with: " + arguments[0]);
                dte=arguments[0];
            }
            if (commandLine.hasOption("v")) {
                String[] arguments = commandLine.getOptionValues("v");
                System.out.println("We try to Pass with: " + arguments[0]);
                vol=arguments[0];
            }
            if (commandLine.hasOption("h")) {
                String[] arguments = commandLine.getOptionValues("h");
                printHelp(
                        posixOptions, // опции по которым составляем help
                        80, // ширина строки вывода
                        "Options", // строка предшествующая выводу
                        "-- HELP --", // строка следующая за выводом
                        3, // число пробелов перед выводом опции
                        5, // число пробелов перед выводом опцисания опции
                        true, // выводить ли в строке usage список команд
                        System.out // куда производить вывод
                );
            }
            start = new Parametrs(log,pas,res,rol,dts,dte,vol);


            return start;
        } catch (ParseException e) {
            printHelp(
                    posixOptions, // опции по которым составляем help
                    80, // ширина строки вывода
                    "Options", // строка предшествующая выводу
                    "-- HELP --", // строка следующая за выводом
                    3, // число пробелов перед выводом опции
                    5, // число пробелов перед выводом опцисания опции
                    true, // выводить ли в строке usage список команд
                    System.out // куда производить вывод
            );
            System.exit(6);
            return start;
        }
    }public static void printHelp(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out)
    {
        final String commandLineSyntax = "java test.jar";//подсказка по запуску самой программы
        final PrintWriter writer = new PrintWriter(out);// куда печатаем help
        final HelpFormatter helpFormatter = new HelpFormatter();// создаем объект для вывода help`а
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);//формирование справки
        writer.flush(); // вывод
    }
}
