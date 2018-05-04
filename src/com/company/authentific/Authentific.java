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
            logger.error("User {} has not role parameter", param.getLogin());
            conn.close();
            System.exit(3);
        } else if (param.hasNotRes()) {
            logger.error("User {} has not res parameter", param.getLogin());
            conn.close();
            System.exit(4);
        }

        try {
            Roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authentificFromAdress(param, conn) ) {
                logger.error("Can not authentificate: {} is wrong res for user {}", param.getRes(), param.getLogin());
                conn.close();
                System.exit(4);
            }
            logger.debug("Authentification success");

        } catch (IllegalArgumentException e) {
            logger.error("Can not authentificate: {} is wrong role for user {}", param.getRole(), param.getLogin());
            logger.error(e);
            conn.close();
            System.exit(3);
        }
    }
}
