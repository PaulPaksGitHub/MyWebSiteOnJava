package test.java.Authorizaion;

import main.java.com.company.SysExits;
import main.java.com.company.authorization.Authorization;
import main.java.com.company.parameters.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class AuthorizationTest {

    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";
    private Connection conn;
    private Parameters param = new Parameters();
    private Authorization auth;


    @Before
    public void initTest() throws SQLException {
        auth = new Authorization();
        conn = DriverManager.getConnection(url, dbUser, dbPassword);
    }

    @After
    public void afterTest() throws SQLException {
        auth = null;
        conn.close();
    }

    @Test
    public void testIsAuthorizable() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole("READ");
        assertEquals(SysExits.valueOf("EXIT0"), auth.isAuthorizable(param, conn));

        param.setLogin("pa");
        param.setRes("A");
        param.setRole("EXECUTE");
        assertEquals(SysExits.valueOf("EXIT4"), auth.isAuthorizable(param, conn));

        param.setLogin("pa");
        param.setRes("TEST");
        param.setRole("READ");
        assertEquals(SysExits.valueOf("EXIT4"), auth.isAuthorizable(param, conn));

        param.setLogin("pa");
        param.setRes(null);
        param.setRole("EXECUTE");
        assertEquals(SysExits.valueOf("EXIT4"), auth.isAuthorizable(param, conn));

        param.setLogin("pa");
        param.setRes("A");
        param.setRole(null);
        assertEquals(SysExits.valueOf("EXIT3"), auth.isAuthorizable(param, conn));

        param.setLogin("pa");
        param.setRes("A");
        param.setRole("KITTY");
        assertEquals(SysExits.valueOf("EXIT3"), auth.isAuthorizable(param, conn));
    }
}