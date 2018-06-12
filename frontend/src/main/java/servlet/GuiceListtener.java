package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import dao.AccountingDAO;
import dao.AuthentificatonDAO;
import dao.AuthorizationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import servlet.ajax.ActivityServlet;
import servlet.ajax.AuthorityServlet;
import servlet.ajax.UserServlet;
import servlet.echo.EchoServlet;
import servlet.echo.GetServlet;
import servlet.echo.PostServlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class GuiceListtener extends GuiceServletContextListener {
    private static final Logger logger = LogManager.getLogger(GuiceListtener.class);
    private static String url;
    private static String dbUser;
    private static String dbPassword;
    private static DataSource pool;
    private static EntityManagerFactory entityManagerFactory;
    private static Gson gson;

    @Override
    protected Injector getInjector() {
        setDbUrl(); // Конфигурирует пути в зависимости от доступной БД
		migrate(); // Миграции БД
        // инициализация EntityManagerFactory
        HashMap<String, String> props = new HashMap<>();
        if (url.split(":")[1].equals("h2")) {
            props.put("javax.persistence.jdbc.driver", "org.h2.Driver");
            props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        }
        props.put("javax.persistence.jdbc.url", url);
        props.put("javax.persistence.jdbc.user", dbUser);
        props.put("javax.persistence.jdbc.password", dbPassword);
        entityManagerFactory = Persistence.createEntityManagerFactory("EnManager", props);

        // Создание пула коннектов
        try {
            pool = new DataSource();
        } catch (IOException | SQLException | PropertyVetoException e) {
            logger.error("Pool cannot be created: {}", e);
        }
        // инициализация Gson
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        this.gson = builder.create();

        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/echo/post").with(PostServlet.class);
                serve("/echo/get").with(GetServlet.class);
                serve("/echo/*").with(EchoServlet.class);
                serve("/ajax/authority").with(AuthorityServlet.class);
                serve("/ajax/user").with(UserServlet.class);
                serve("/ajax/activity").with(ActivityServlet.class);

                filter("/*").through(ContentTypeFilter.class);

                bindListener(Matchers.any(), new Log4JTypeListener());
            }
        });
    }

    class Log4JTypeListener implements TypeListener {
        public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
            Class<?> clazz = typeLiteral.getRawType();
            while (clazz != null) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.getType() == Logger.class &&
                            field.isAnnotationPresent(LogAnot.class)) {
                        typeEncounter.register(new Log4JMembersInjector<T>(field));
                    }
                    if (field.getType() == AuthentificatonDAO.class) {
                        typeEncounter.register(new AuthentificatonDaoInjector<T>(field));
                    }
                    if (field.getType() == AuthorizationDAO.class) {
                        typeEncounter.register(new AuthorizationDaoInjector<T>(field));
                    }
                    if (field.getType() == AccountingDAO.class) {
                        typeEncounter.register(new AccountingDaoInjector<T>(field));
                    }
                    if (field.getType() == Connection.class) {
                        typeEncounter.register(new ConnectionInjector<T>(field));
                    }
                    if (field.getType() == EntityManager.class) {
                        typeEncounter.register(new EntityManagerInjector<T>(field));
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }
    }

    class Log4JMembersInjector<T> implements MembersInjector<T> {
        private final Field field;
        private final Logger logger;

        Log4JMembersInjector(Field field) {
            this.field = field;
            this.logger = LogManager.getLogger(field.getDeclaringClass());
            field.setAccessible(true);
        }

        public void injectMembers(T t) {
            try {
                field.set(t, logger);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class AuthentificatonDaoInjector<T> implements MembersInjector<T> {
        private final Field field;
        AuthentificatonDAO auth;

        AuthentificatonDaoInjector(Field field) {
            this.field = field;
            field.setAccessible(true);
            this.auth = new AuthentificatonDAO(
                    entityManagerFactory.createEntityManager(), gson, LogManager.getLogger(field.getDeclaringClass()));//
        }

        public void injectMembers(T t) {
            try {
                field.set(t, auth);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class ConnectionInjector<T> implements MembersInjector<T> {
        private final Field field;
        Connection conn;

        ConnectionInjector(Field field) {
            this.field = field;
            try {
                this.conn = pool.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            field.setAccessible(true);
        }

        public void injectMembers(T t) {
            try {
                field.set(t, conn);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    class EntityManagerInjector<T> implements MembersInjector<T> {
        private final Field field;
        EntityManager em;

        EntityManagerInjector(Field field) {
            this.field = field;
            this.em = entityManagerFactory.createEntityManager();
            field.setAccessible(true);
        }

        public void injectMembers(T t) {
            try {
                field.set(t, em);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class AuthorizationDaoInjector<T> implements MembersInjector<T> {
        private final Field field;
        AuthorizationDAO dao;

        AuthorizationDaoInjector(Field field) {
            this.field = field;
            field.setAccessible(true);
            this.dao = new AuthorizationDAO(
                    entityManagerFactory.createEntityManager(), gson, LogManager.getLogger(field.getDeclaringClass()));
        }

        public void injectMembers(T t) {
            try {
                field.set(t, dao);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class AccountingDaoInjector<T> implements MembersInjector<T> {
        private final Field field;
        AccountingDAO dao;

        AccountingDaoInjector(Field field) {
            this.field = field;
            field.setAccessible(true);
            this.dao = new AccountingDAO(
                    entityManagerFactory.createEntityManager(), gson, LogManager.getLogger(field.getDeclaringClass()));
        }

        public void injectMembers(T t) {
            try {
                field.set(t, dao);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setDbUrl() {
        URI dbUri = null;
        try {
            if (System.getenv("DATABASE_URL") != null) {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
        } catch (URISyntaxException e) {
            logger.error("Cannot get db url {}", e);
        }
        if (dbUri == null) {
            url = "jdbc:h2:file:./data/db;MODE=PostgreSQL";
            dbUser = "sa";
            dbPassword = "";
        } else {
            url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dbUser = dbUri.getUserInfo().split(":")[0];
            dbPassword = dbUri.getUserInfo().split(":")[1];
		}
    }

    private void migrate() {
        logger.debug("START MIGRATIONS");
        Flyway flyway = new Flyway();
        if (url.split(":")[1].equals("h2")) {
            flyway.setLocations("db/migration/h2");
            logger.debug("Using H2 database");
        } else {
            flyway.setLocations("db/migration/pg");
            logger.debug("Using PostgreSQL database");
        }
        flyway.setDataSource(url, dbUser, dbPassword);
        flyway.migrate();
        logger.debug("END MIGRATIONS");
    }

    public class DataSource {
        private DataSource datasource;
        private ComboPooledDataSource cpds;

        private DataSource() throws IOException, SQLException, PropertyVetoException {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver"); //loads the jdbc driver
            cpds.setJdbcUrl(url);
            cpds.setUser(dbUser);
            cpds.setPassword(dbPassword);

        }

        public DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
            if (datasource == null) {
                datasource = new DataSource();
                return datasource;
            } else {
                return datasource;
            }
        }

        public Connection getConnection() throws SQLException {
            return this.cpds.getConnection();
        }

    }
}