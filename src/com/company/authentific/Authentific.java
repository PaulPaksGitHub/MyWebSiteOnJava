package com.company.authentific;

import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Authentific {
    private static final Logger logger = LogManager.getLogger(Authentific.class);

    public void isAuthentificated(Parameters param, Connection conn) throws SQLException {
        if (param.hasNotRole()){
            logger.error("Has not role");
            conn.close();
            System.exit(3);
        } else if (param.hasNotRes()) {
            logger.error("Has not res");
            conn.close();
            System.exit(4);
        }

        try {
            Roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authentificFromAdress(param, conn) ) {
                logger.error("Can not authentificate: '" + param.getRes() + "' is wrong res");
                conn.close();
                System.exit(4);
            }
            logger.debug("Authentification success");
        } catch (IllegalArgumentException e) {
            logger.error("Can not authentificate: '" + param.getRole() + "' is wrong role");
            logger.error(e);
            conn.close();
            System.exit(3);
        }
    }
}
