package authentifiacation;

import com.company.SysExits;
import com.company.authentification.Authentification;
import com.company.authentification.AuthentificatonDAO;
import com.company.authentification.User;
import com.company.parameters.Parameters;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthentificationTest {

    private Parameters param;
    private Authentification auth;
    private AuthentificatonDAO dao;

    @Before
    public void initTest() throws SQLException {
        dao = mock(AuthentificatonDAO.class);
        auth = new Authentification(dao);
        param = new Parameters();
    }

    @Test
    public void testIsLoginRegex() {
        assertTrue(auth.isLoginRegex("TrueLogin"));
        assertFalse(auth.isLoginRegex("False_Password-123"));
    }

    @Test
    public void testIsAuthentificable() throws SQLException {
        param.setLogin("pa");
        param.setPass("12");
        when(dao.getUserFromLogin(param.getLogin(), null)).thenReturn
                (new User("pa", "1c597a01f767c2a9de609927a87946cc", "6B3BP7O4C8PJRMAA1VHAJD3YSEI0LITT"));
        assertEquals(SysExits.EXIT0, auth.isAuthentificable(param, null));
    }

    @Test
    public void testIsAuthentificable1() throws SQLException {
        param.setLogin(null);
        param.setPass("12");
        assertEquals(SysExits.EXIT1, auth.isAuthentificable(param, null));
    }

    @Test
    public void testIsAuthentificable2() throws SQLException {
        param.setLogin("pa");
        param.setPass(null);
        assertEquals(SysExits.EXIT2, auth.isAuthentificable(param, null));
    }

    @Test
    public void testIsAuthentificable3() throws SQLException {
        param.setLogin("pa");
        param.setPass("34");
        when(dao.getUserFromLogin(param.getLogin(), null)).thenReturn
                (new User("pa", "1c597a01f767c2a9de609927a87946cc", "6B3BP7O4C8PJRMAA1VHAJD3YSEI0LITT"));
        assertEquals(SysExits.EXIT2, auth.isAuthentificable(param, null));
    }

    @Test
    public void testIsAuthentificable4() throws SQLException {
        param.setLogin("TEST");
        param.setPass("12");
        when(dao.getUserFromLogin(param.getLogin(), null)).thenReturn
                (null);
        assertEquals(SysExits.EXIT1, auth.isAuthentificable(param, null));
    }
}