package parameters;

import com.company.parameters.Parameters;
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
    }

    @Test
    public void testIsParamEmpty1() {
        param.setLogin("pa");
        assertFalse(param.isParamEmpty());
    }

    @Test
    public void testHasLogin() {
        assertFalse(param.hasLogin());
    }

    @Test
    public void testHasLogin1() {
        param.setLogin("pa");
        assertTrue(param.hasLogin());
    }

    @Test
    public void testHasPassword() {
        assertFalse(param.hasPassword());
    }

    @Test
    public void testHasPassword1() {
        param.setPass("12");
        assertTrue(param.hasPassword());
    }

    @Test
    public void testHasNotRole() {
        assertTrue(param.hasNotRole());
    }

    @Test
    public void testHasNotRole1() {
        param.setRole("READ");
        assertFalse(param.hasNotRole());
    }

    @Test
    public void testHasNotRes() {
        assertTrue(param.hasNotRes());
    }

    @Test
    public void testHasNotRes1() {
        param.setRes("aaa");
        assertFalse(param.hasNotRes());
    }

    @Test
    public void testCanAutorize() {
        param.setRes("a.b");
        param.setRole("read");
        assertTrue(param.canAuthorize());
    }

    @Test
    public void testCanAutorize1() {
        param.setRes("a.b");
        param.setRole(null);
        assertTrue(param.canAuthorize());
    }

    @Test
    public void testCanAutorize2() {
        param.setRes(null);
        param.setRole("role");
        assertTrue(param.canAuthorize());
    }

    @Test
    public void testCanAutorize3() {
        param.setRes(null);
        param.setRole(null);
        assertFalse(param.canAuthorize());
    }

    @Test
    public void testCanAuthehtific() {
        param.setLogin("pa");
        param.setPass("12");
        assertTrue(param.canAuthentific());
    }

    @Test
    public void testCanAuthehtific1() {
        param.setLogin(null);
        param.setPass("12");
        assertTrue(param.canAuthentific());
    }

    @Test
    public void testCanAuthehtific2() {
        param.setLogin("pa");
        param.setPass(null);
        assertTrue(param.canAuthentific());
    }

    @Test
    public void testCanAuthehtific3() {
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
    }

    @Test
    public void testCanAccaunt1() {
        param.setDs(null);
        param.setDe("1212-12-12");
        param.setVol("3");
        assertTrue(param.canAccaunt());
    }

    @Test
    public void testCanAccaunt2() {
        param.setDs("2112-01-01");
        param.setDe(null);
        param.setVol("3");
        assertTrue(param.canAccaunt());
    }

    @Test
    public void testCanAccaunt3() {
        param.setDs("2112-01-01");
        param.setDe("1212-12-12");
        param.setVol(null);
        assertTrue(param.canAccaunt());
    }

    @Test
    public void testCanAccaunt4() {
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
    }

    @Test
    public void testHasLoginAndPass1() {
        param.setLogin(null);
        param.setPass("12");
        assertFalse(param.hasLoginAndPass());
    }

    @Test
    public void testHasLoginAndPass2() {
        param.setLogin("pa");
        param.setPass(null);
        assertFalse(param.hasLoginAndPass());
    }

    @Test
    public void testHasLoginAndPass3() {
        param.setLogin(null);
        param.setPass(null);
        assertFalse(param.hasLoginAndPass());
    }
}