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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import servlet.ajax.ActivityServlet;
import servlet.ajax.AuthorityServlet;
import servlet.ajax.UserServlet;
import servlet.echo.EchoServlet;
import servlet.echo.GetServlet;
import servlet.echo.PostServlet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuiceListtener extends GuiceServletContextListener {
    private static final Logger logger = LogManager.getLogger(GuiceListtener.class);
    private static final Gson gson = new Gson();
    private static String url = "jdbc:h2:file:./data/db";
    private static String dbUser = "sa";
    private static String dbPassword = "";



    @Override
    protected Injector getInjector() {
        logger.debug("START MIGRATIONS");
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, dbUser, dbPassword);
        flyway.migrate();
        logger.debug("END MIGRATIONS");
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/echo/post").with(PostServlet.class);
                serve("/echo/get").with(GetServlet.class);
                serve("/echo/*").with(EchoServlet.class);
                serve("/ajax/authority").with(AuthorityServlet.class);
                serve("/ajax/user").with(UserServlet.class);
                serve("/ajax/activity").with(ActivityServlet.class);

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
                    if (field.getType() == Provider.class &&
                            field.isAnnotationPresent(ProviderAnot.class)) {
                        typeEncounter.register(new ProviderInjector<T>(field));
                    }
                    if (field.getType() == Connection.class &&
                            field.isAnnotationPresent(ConnectionAnot.class)) {
                        typeEncounter.register(new ConnectionInjector<T>(field));
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

    static class ConnectionInjector<T> implements MembersInjector<T> {
        private Field field;
        private Connection conn;

        ConnectionInjector(Field field) {
            this.field = field;
            try {
                this.conn = DriverManager.getConnection(url, dbUser, dbPassword);
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

    public interface Provider<T> {
        T get();
    }

    public class ProviderInjector<Gson> implements MembersInjector<Gson> {
        private final Field field;
        private final Gson provider;

        public ProviderInjector(Field field) {
            this.field = field;

            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            com.google.gson.Gson gson = builder.create();

            this.provider = (Gson) gson;
            field.setAccessible(true);
        }

        @Override
        public void injectMembers(Gson t) {
            try {
                field.set(t, provider);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}