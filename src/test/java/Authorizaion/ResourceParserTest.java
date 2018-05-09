package test.java.Authorizaion;

import main.java.com.company.authorization.ResourceParser;
import main.java.com.company.parameters.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ResourceParserTest {
    private ResourceParser resParser;
    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";
    private Connection conn;
    private Parameters param = new Parameters();

    public ResourceParserTest() throws SQLException {
    }

    @Before
    public void initTest() throws SQLException {
        resParser = new ResourceParser();
        conn = DriverManager.getConnection(url, dbUser, dbPassword);
    }

    @After
    public void afterTest() throws SQLException {
        resParser = null;
        conn.close();
    }

    @Test
    public void testAuthentificFromAdressOneSQL() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole("READ");
        assertTrue(resParser.authentificFromAdressOneSQL(param, conn));

        param.setLogin("pa");
        param.setRes("A");
        param.setRole("EXECUTE");
        assertFalse( resParser.authentificFromAdressOneSQL(param, conn));
    }
}
