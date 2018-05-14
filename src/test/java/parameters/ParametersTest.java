package test.java.parameters;

import main.java.com.company.parameters.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParametersTest {

    private Parameters param;

    @Before
    public void initTest() {
        param = new Parameters();
    }

    @After
    public void afterTest() {
        param = null;
    }

    @Test
    public void testIsParamEmpty() {
        assertTrue(param.isParamEmpty());

        param.setLogin("pa");
        assertFalse(param.isParamEmpty());
    }

    @Test
    public void testHasLogin() {
        assertFalse(param.hasLogin());

        param.setLogin("pa");
        assertTrue(param.hasLogin());
    }

    @Test
    public void testHasPassword() {
        assertFalse(param.hasPassword());

        param.setPass("12");
        assertTrue(param.hasPassword());
    }

    @Test
    public void testHasNotRole() {
        assertTrue(param.hasNotRole());

        param.setRole("READ");
        assertFalse(param.hasNotRole());
    }

    @Test
    public void testHasNotRes() {
        assertTrue(param.hasNotRes());

        param.setRes("aaa");
        assertFalse(param.hasNotRes());
    }

    @Test
    public void testCanAutorize() {
        param.setRes("a.b");
        param.setRole("read");
        assertTrue(param.canAuthorize());

        param.setRes("a.b");
        param.setRole(null);
        assertTrue(param.canAuthorize());

        param.setRes(null);
        param.setRole("role");
        assertTrue(param.canAuthorize());

        param.setRes(null);
        param.setRole(null);
        assertFalse(param.canAuthorize());
    }

    @Test
    public void testCanAuthehtific() {
        param.setLogin("pa");
        param.setPass("12");
        assertTrue(param.canAuthentific());

        param.setLogin(null);
        param.setPass("12");
        assertTrue(param.canAuthentific());

        param.setLogin("pa");
        param.setPass(null);
        assertTrue(param.canAuthentific());

        param.setLogin(null);
        param.setPass(null);
        assertFalse(param.canAuthentific());
    }

    @Test
    public void testCanAccaunt() {
        param.setDs("2112-01-01");
        param.setDe("1212-12-12");
        param.setVol("3");
        assertTrue(param.canAccaunt());

        param.setDs(null);
        param.setDe("1212-12-12");
        param.setVol("3");
        assertTrue(param.canAccaunt());

        param.setDs("2112-01-01");
        param.setDe(null);
        param.setVol("3");
        assertTrue(param.canAccaunt());

        param.setDs("2112-01-01");
        param.setDe("1212-12-12");
        param.setVol(null);
        assertTrue(param.canAccaunt());

        param.setDs(null);
        param.setDe(null);
        param.setVol(null);
        assertFalse(param.canAccaunt());
    }

    @Test
    public void testHasLoginAndPass() {
        param.setLogin("pa");
        param.setPass("12");
        assertTrue(param.hasLoginAndPass());

        param.setLogin(null);
        param.setPass("12");
        assertFalse(param.hasLoginAndPass());

        param.setLogin("pa");
        param.setPass(null);
        assertFalse(param.hasLoginAndPass());

        param.setLogin(null);
        param.setPass(null);
        assertFalse(param.hasLoginAndPass());
    }
}