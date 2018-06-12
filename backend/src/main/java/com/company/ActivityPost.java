package com.company;

import com.company.accounting.Accounting;
import com.company.authentification.Authentification;
import com.company.authorization.Authorization;
import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class ActivityPost {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public String getActivity(Parameters param, EntityManager em) throws SQLException {
        Authorization autorization = new Authorization();
        Authentification authentific = new Authentification();
        Accounting accounting = new Accounting();
        String success = "";

        if (param.canAuthentific() && param.canAuthorize() && param.canAccaunt()) {
            success += message(authentific.isAuthentificable(param, em));
            success += message(autorization.isAuthorizable(param, em));
            success += message(accounting.isAccountable(param, em));
        } else if (param.canAuthentific() && param.canAuthorize()) {
            success += message(authentific.isAuthentificable(param, em));
            success += message(autorization.isAuthorizable(param, em));
        } else if (param.canAuthentific()) {
            success += message(authentific.isAuthentificable(param, em));
        }

        if (success.equals("")) {
            return "Seccesful";
        } else {
            return success;
        }
    }

    //0 - успех
    //1 - неизвестный логин
    //2 - неверный пароль
    //3 - неизвестная роль
    //4 - нет доступа
    //5 - некорректная активность (невалидная дата или объем)
    //6 - все прочие ошибки

    private static String message(SysExits exit) throws SQLException {
        int code = checkExit(exit);
        if (code == 1) return "Unknown login ";
        else if (code == 2) return "Wrong password ";
        else if (code == 3) return "Unknown role ";
        else if (code == 4) return "Hasn't access rights ";
        else if (code == 5) return "Uncorrect date or value ";
        else if (code == 6) return "Unedentified error ";
        return "";
    }

    private static int checkExit(SysExits exit) throws SQLException {
        if (exit.equals(SysExits.EXIT0)) {
            logger.info("Operation complete");
        } else {
            logger.error("Operation crashed");
            return exit.getExitCode();
        }
        return 0;
    }
}
