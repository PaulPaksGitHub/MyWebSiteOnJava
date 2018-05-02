package com.company.flyway_migrations;

import org.flywaydb.core.Flyway;

public class FlywayCheck {
    public void checkDB() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:file:./data/db", "sa", "");
        flyway.migrate();
    }
}
