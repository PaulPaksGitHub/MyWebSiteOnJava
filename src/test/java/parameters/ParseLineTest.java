package test.java.parameters;

import main.java.com.company.parameters.Parameters;
import main.java.com.company.parameters.ParseLine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParseLineTest {
    private ParseLine parser = new ParseLine();
    private Parameters param = new Parameters();

    @Test
    public void testParse() {
        param.setH(true);
        assertEquals(param.isH(), parser.parse(new String[]{}).isH());
        param.setH(false);
    }

    @Test
    public void testParse1() {
        param.setLogin("test");
        assertEquals(param.getLogin(), parser.parse(new String[]{"-login", "test"}).getLogin());
        param.setLogin(null);
    }

    @Test
    public void testParse2() {
        param.setPass("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-pass", "test"}).getPass());
        param.setPass(null);
    }

    @Test
    public void testParse3() {
        param.setRole("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-role", "test"}).getPass());
        param.setRole(null);
    }

    @Test
    public void testParse4() {
        param.setRes("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-res", "test"}).getPass());
        param.setRes(null);
    }

    @Test
    public void testParse5() {
        param.setDs("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-ds", "test"}).getPass());
        param.setDs(null);
    }

    @Test
    public void testParse6() {
        param.setDe("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-de", "test"}).getPass());
        param.setDe(null);
    }

    @Test
    public void testParse7() {
        param.setVol("test");
        assertEquals(param.getPass(), parser.parse(new String[]{"-vol", "test"}).getPass());
        param.setVol(null);
    }
}

