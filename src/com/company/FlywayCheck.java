package com.company;

import org.flywaydb.core.Flyway;

import java.sql.*;

public class FlywayCheck {
    public void checkDB() {
        try (Connection conn = DriverManager.
                getConnection("jdbc:h2:./data/db", "sa", "")) {
            Statement st = conn.createStatement();
            st.execute("select * from users");
            st.execute("select * from res");
        } catch (SQLException e) {
            // Create the FlywayCheck instance
            Flyway flyway = new Flyway();

            // Point it to the database
            flyway.setDataSource("jdbc:h2:file:./data/db", "sa", "");

            // Start the migration
            flyway.migrate();
        }
    }
}
