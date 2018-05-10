package test.java.accounting;

import main.java.com.company.Main;
import main.java.com.company.SysExits;
import main.java.com.company.accounting.Accounting;
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

public class AccountingTest {

    private Accounting acc;
    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";
    private Connection conn;
    private Parameters param = new Parameters();

    public AccountingTest() throws SQLException {
    }

    @Before
    public void initTest() throws SQLException {
        Main.checkDBconnection();
        acc = new Accounting();
        conn = DriverManager.getConnection(url, dbUser, dbPassword);
    }

    @After
    public void afterTest() throws SQLException {
        acc = null;
        conn.close();
    }

    @Test
    public void testHasTrueVol() throws SQLException {
        param.setVol("4");
        assertTrue(acc.hasTrueVol(param, conn));

        param.setVol("4.2");
        assertFalse(acc.hasTrueVol(param, conn));
    }

    @Test
    public void testHasTrueData() throws SQLException {
        param.setDs("2001-12-12");
        param.setDe("2004-12-11");
        assertTrue(acc.hasTrueData(param, conn));

        param.setDs("12-12-2001");
        param.setDe("2004-12-11");
        assertFalse(acc.hasTrueData(param, conn));

        param.setDs("2001-12-12");
        param.setDe("12-11-2004");
        assertFalse( acc.hasTrueData(param, conn));

        param.setDs("2001-20-12");
        param.setDe("2004-12-11");
        assertFalse( acc.hasTrueData(param, conn));
    }

    @Test
    public void testIsAccountable() throws SQLException{
        param.setLogin("ha");
        param.setVol("4");
        param.setDs("2001-12-12");
        param.setDe("2004-12-11");
        assertEquals(SysExits.valueOf("EXIT0"), acc.isAccountable(param, conn));
    }
}