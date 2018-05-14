package test.java.accounting;

import main.java.com.company.SysExits;
import main.java.com.company.accounting.Accounting;
import main.java.com.company.accounting.AccountingDAO;
import main.java.com.company.parameters.Parameters;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountingTest {

    private Accounting acc;
    private Parameters param = new Parameters();
    private AccountingDAO dao;

    @Before
    public void initTest() throws SQLException {
        dao = mock(AccountingDAO.class);
        acc = new Accounting(dao);
    }

    @Test
    public void testHasTrueVol() throws SQLException {
        param.setVol("4");
        assertTrue(acc.hasTrueVol(param));
    }

    @Test
    public void testHasTrueVol1() throws SQLException {
        param.setVol("4.2");
        assertFalse(acc.hasTrueVol(param));
    }

    @Test
    public void testHasTrueData1() throws SQLException {
        param.setDs("2001-12-12");
        param.setDe("2004-12-11");
        assertTrue(acc.hasTrueData(param));
    }

    @Test
    public void testHasTrueData2() throws SQLException {
        param.setDs("12-12-2001");
        param.setDe("2004-12-11");
        assertFalse(acc.hasTrueData(param));
    }

    @Test
    public void testHasTrueData3() throws SQLException {
        param.setDs("2001-12-12");
        param.setDe("12-11-2004");
        assertFalse(acc.hasTrueData(param));
    }

    @Test
    public void testHasTrueData4() throws SQLException {
        param.setDs("2001-20-12");
        param.setDe("2004-12-11");
        assertFalse(acc.hasTrueData(param));
    }

    @Test
    public void testIsAccountable() throws SQLException {
        param.setLogin("ha");
        param.setVol("4");
        param.setDs("2001-12-12");
        param.setDe("2004-12-11");
        when(dao.writeUserToTable(param, null)).thenReturn(true);
        assertEquals(SysExits.valueOf("EXIT0"), acc.isAccountable(param, null));
    }
}