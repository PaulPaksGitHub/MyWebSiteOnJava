package test.java;

import main.java.com.company.Main;
import org.junit.Test;

import java.sql.SQLException;

public class MainTest {
    @Test
    public void testMain() throws SQLException {
        Main.main(new String[]{"-login", "pa", "-pass", "12"});
    }

}