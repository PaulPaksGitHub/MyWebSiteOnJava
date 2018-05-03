package com.company.authentific;

import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Authentific {
    private static final Logger logger = LogManager.getLogger(Authentific.class);

    public void isAuthentificated(Parameters param, Connection conn) throws SQLException {
        try {
            Roles.roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authentificFromAdress(param, conn)) {
                logger.error("Can not authentificate: '" + param.getRole() + "' is wrong resource");
                System.exit(4);
            }
            logger.debug("Authentification success");
        } catch (IllegalArgumentException e) {
            logger.error("Can not authentificate: '" + param.getRole() + "' is wrong role");
            logger.error(e);
            System.exit(3);
        }
    }
}
