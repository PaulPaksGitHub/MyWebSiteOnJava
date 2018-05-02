package com.company.accounting;

import com.company.parametrs.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Accounting {
    private static final Logger logger = LogManager.getLogger(Accounting.class);

    public void isAccounted(Parameters param) {
        if (hasTrueData(param) && hasTrueVol(param)) {
            logger.debug("Accaunting success: " +
                    param.getLogin() + ", " +
                    param.getDs() + ", " +
                    param.getDe() + ", " +
                    param.getVol());
        }
    }

    private boolean hasTrueData(Parameters param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(param.getDs(), formatter);
            LocalDate.parse(param.getDe(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            logger.error("Can not account: '" + param.getDs() + " " + param.getDe() + "' is wrong data");
            logger.error(e);
            System.exit(5);
            return false;
        }
    }

    private boolean hasTrueVol(Parameters param) {
        try {
            int a = Integer.parseInt(param.getVol());
            return true;
        } catch (NumberFormatException e) {
            logger.error("Can not account: '" + param.getVol() + "' is wrong vol");
            logger.error(e);
            System.exit(5);
            return false;
        }
    }
}
