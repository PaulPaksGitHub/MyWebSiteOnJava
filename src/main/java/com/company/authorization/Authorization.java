package main.java.com.company.authorization;

import main.java.com.company.SysExits;
import main.java.com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Authorization {
    private static final Logger logger = LogManager.getLogger(Authorization.class);

    public SysExits isAuthorizable(Parameters param, Connection conn) throws SQLException {
        if (param.hasNotRole()) {
            logger.error("User {} has not role parameter", param.getLogin());
            return SysExits.valueOf("EXIT3");
//            conn.close();
//            System.exit(3);
        } else if (param.hasNotRes()) {
            logger.error("User {} has not res parameter", param.getLogin());
            return SysExits.valueOf("EXIT4");
//            conn.close();
//            System.exit(4);
        }

        try {
            Roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authentificFromAdressOneSQL(param, conn)) {
                logger.error("Can not authentificate: {} is wrong res for user {} with role {}",
                        param.getRes(), param.getLogin(), param.getRole());
                return SysExits.valueOf("EXIT4");
//                conn.close();
//                System.exit(4);
            }
            logger.debug("Authentification success");
            return SysExits.valueOf("EXIT0");

        } catch (IllegalArgumentException e) {
            logger.error("Can not authentificate: {} is wrong role for user {}", param.getRole(), param.getLogin());
            logger.error(e);
            return SysExits.valueOf("EXIT3");
//            conn.close();
//            System.exit(3);
        }
    }
}
