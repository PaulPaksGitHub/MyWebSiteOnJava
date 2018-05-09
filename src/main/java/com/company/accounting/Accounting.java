package main.java.com.company.accounting;

import main.java.com.company.SysExits;
import main.java.com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Accounting {
    private static final Logger logger = LogManager.getLogger(Accounting.class);

    public SysExits isAccountable(Parameters param, Connection conn) throws SQLException {
        if (SysExits.valueOf("EXIT0").equals(hasTrueData(param, conn)) &&
                SysExits.valueOf("EXIT0").equals(hasTrueVol(param, conn))) {
            logger.debug("Accaunting success: {}, {}, {}, {}",
                    param.getLogin(),
                    param.getDs(),
                    param.getDe(),
                    param.getVol());

            PreparedStatement st = conn.prepareStatement("insert into acc (login, ds, de, vol) values (?,?,?,?) ");
            st.setString(1, param.getLogin());
            st.setString(2, param.getDs());
            st.setString(3, param.getDe());
            st.setString(4, param.getVol());
            st.executeUpdate();
            st.close();
        }
        return SysExits.valueOf("EXIT0");
    }

    public SysExits hasTrueData(Parameters param, Connection conn) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(param.getDs(), formatter);
            LocalDate.parse(param.getDe(), formatter);
            return SysExits.valueOf("EXIT0");
        } catch (DateTimeParseException e) {
            logger.error("Can not account: {}, {} is wrong data", param.getDs(), param.getDe());
            logger.error(e);
            conn.close();
            return SysExits.valueOf("EXIT5");
        }
    }

    public SysExits hasTrueVol(Parameters param, Connection conn) throws SQLException {
        try {
            int a = Integer.parseInt(param.getVol());
            return SysExits.valueOf("EXIT0");
        } catch (NumberFormatException e) {
            logger.error("Can not account: {} is wrong vol", param.getVol());
            logger.error(e);
            conn.close();
            return SysExits.valueOf("EXIT5");
        }
    }
}