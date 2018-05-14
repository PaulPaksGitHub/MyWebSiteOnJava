package main.java.com.company.authentification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthentificatonDAO {
    public User getUserFromLogin(String login, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users where login = ?");
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        rs.close();
        st.close();
        if (rs.next()) {
            User user = new User(
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            return user;
        }
        return new User("", "", "");
    }
}
