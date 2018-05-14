package test.java.parameters;

import main.java.com.company.parameters.Parameters;
import main.java.com.company.parameters.ParseLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParseLineTest {

    private ParseLine parser = new ParseLine();
    private Parameters param = new Parameters();

    @Test
    public void testParse() {
        param.setH(true);
        assertEquals(param.isH() , parser.parse(new String[]{}).isH());
        param.setH(false);

        param.setLogin("test");
        assertEquals(param.getLogin(), parser.parse(new String[]{"-login","test"}).getLogin());
        param.setLogin(null);

        param.setPass("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-pass","test"}).getPass());
        param.setPass(null);

    }
}