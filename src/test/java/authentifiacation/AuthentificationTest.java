package test.java.authentifiacation;

import main.java.com.company.SysExits;
import main.java.com.company.authentification.Authentification;
import main.java.com.company.parameters.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AuthentificationTest {

    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";
    private Connection conn;
    private Parameters param = new Parameters();
    private Authentification auth;

    @Before
    public void initTest() throws SQLException {
        auth = new Authentification();
        conn = DriverManager.getConnection(url, dbUser, dbPassword);
    }

    @After
    public void afterTest() throws SQLException {
        auth = null;
        conn.close();
    }

    @Test
    public void testIsLoginRegex() {
        assertTrue( auth.isLoginRegex("TrueLogin"));
        assertFalse(auth.isLoginRegex("False_Password-123"));
    }

    @Test
    public void testGetUserFromLogin() throws SQLException {
        assertEquals("pa", auth.getUserFromLogin("pa",conn).getLogin());
        assertEquals("", auth.getUserFromLogin("TEST",conn).getLogin());
    }

    @Test
    public void testIsAuthentificable() throws SQLException {
        param.setLogin("pa");
        param.setPass("12");
        assertEquals(SysExits.valueOf("EXIT0"), auth.isAuthentificable(param, conn));

        param.setLogin(null);
        param.setPass("12");
        assertEquals(SysExits.valueOf("EXIT1"), auth.isAuthentificable(param, conn));

        param.setLogin("pa");
        param.setPass(null);
        assertEquals(SysExits.valueOf("EXIT2"), auth.isAuthentificable(param, conn));

        param.setLogin("pa");
        param.setPass("34");
        assertEquals(SysExits.valueOf("EXIT2"), auth.isAuthentificable(param, conn));

        param.setLogin("TEST");
        param.setPass("12");
        assertEquals(SysExits.valueOf("EXIT1"), auth.isAuthentificable(param, conn));
    }
}