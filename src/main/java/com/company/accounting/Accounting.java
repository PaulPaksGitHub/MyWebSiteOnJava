package com.company.accounting;

import com.company.SysExits;
import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Accounting {
    private static final Logger logger = LogManager.getLogger(Accounting.class);
    private AccountingDAO dao;

    public SysExits isAccountable(Parameters param, Connection conn) throws SQLException {
        if ( hasTrueData(param) && hasTrueVol(param)) {
            logger.debug("Accaunting success: {}, {}, {}, {}",
                    param.getLogin(),
                    param.getDs(),
                    param.getDe(),
                    param.getVol());
            dao.writeUserToTable(param, conn);
            return SysExits.valueOf("EXIT0");
        }
        else {
            return SysExits.valueOf("EXIT5");
        }
    }

    public boolean hasTrueData(Parameters param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(param.getDs(), formatter);
            LocalDate.parse(param.getDe(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            logger.error("Can not account: {}, {} is wrong data", param.getDs(), param.getDe());
            logger.error(e);
            return false;
        }
    }

    public boolean hasTrueVol(Parameters param) {
        try {
            int a = Integer.parseInt(param.getVol());
            return true;
        } catch (NumberFormatException e) {
            logger.error("Can not account: {} is wrong vol", param.getVol());
            logger.error(e);
            return false;
        }
    }

    public Accounting(AccountingDAO dao) {
        this.dao = dao;
    }

    public Accounting() {
    }
}
