package test.java;

import main.java.com.company.Main;
import org.junit.Test;

import java.sql.SQLException;

public class MainTest {

    @Test
    public void testMain() throws SQLException {
        Main main = new Main();
        main.main(new String[]{"-login", "pa","-pass","12"});
        main.main(new String[]{"-login", "'pa'", "-pass", "'12'", "-res", "'A.B.C'", "-role", "'WRITE'"});
        main.main(new String[]{"-login", "'pa'", "-pass", "'12'", "-res", "'A.B.C'",
                "-role", "'WRITE'", "-ds", "2112-01-01", "-de", "2122-03-05", "-vol", "4"});
    }
}