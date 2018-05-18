package authorizaion;

import com.company.SysExits;
import com.company.authorization.Authorization;
import com.company.authorization.AuthorizationDAO;
import com.company.parameters.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class AuthorizationTest extends Mockito {
    private Parameters param;
    private Authorization auth;
    private AuthorizationDAO dao;

    @Before
    public void initTest() throws SQLException {
        param = new Parameters();
        dao = mock(AuthorizationDAO.class);
        auth = new Authorization(dao);
    }

    @Test
    public void testIsAuthorizable() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole("READ");
        when(dao.getAccessToRes(param, null)).thenReturn(true);
        assertEquals(SysExits.EXIT0, auth.isAuthorizable(param, null));
    }

    @Test
    public void testIsAuthorizable1() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole("EXECUTE");
        when(dao.getAccessToRes(param, null)).thenReturn(false);
        assertEquals(SysExits.EXIT4, auth.isAuthorizable(param, null));}

    @Test
    public void testIsAuthorizable2() throws SQLException {
        param.setLogin("pa");
        param.setRes("TEST");
        param.setRole("READ");
        assertEquals(SysExits.EXIT4, auth.isAuthorizable(param, null));}

    @Test
    public void testIsAuthorizable3() throws SQLException {
        param.setLogin("pa");
        param.setRes(null);
        param.setRole("EXECUTE");
        when(dao.getAccessToRes(param, null)).thenReturn(false);
        assertEquals(SysExits.EXIT4, auth.isAuthorizable(param, null));}

    @Test
    public void testIsAuthorizable4() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole(null);
        when(dao.getAccessToRes(param, null)).thenReturn(false);
        assertEquals(SysExits.EXIT3, auth.isAuthorizable(param, null));}

    @Test
    public void testIsAuthorizable5() throws SQLException {
        param.setLogin("pa");
        param.setRes("A");
        param.setRole("KITTY");
        when(dao.getAccessToRes(param, null)).thenReturn(false);
        assertEquals(SysExits.EXIT3, auth.isAuthorizable(param, null));
    }
}