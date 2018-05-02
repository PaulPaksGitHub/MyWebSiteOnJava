package com.company.authentific;

import com.company.parametrs.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Authentific {
    private static final Logger logger = LogManager.getLogger(Authentific.class);

    public void isAuthentificated(Parameters param) {
        try {
            Roles.roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authentificFromAdress(param)) {
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
