package servlet;

import com.google.gson.Gson;
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

import java.lang.reflect.Field;

public class GuiceListtener extends GuiceServletContextListener {
    private static final Logger logger = LogManager.getLogger(GuiceListtener.class);
    private static final Gson gson = new Gson();

    @Override
    protected Injector getInjector() {
        logger.debug("ACTIVATE");
        //if (true) throw new RuntimeException("1221213213");
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
                    if (field.getType() == Gson.class &&
                            field.isAnnotationPresent(GsonAnot.class)) {
                        typeEncounter.register(new GsonInjector<T>(field));
                    }
                    logger.error("!!!!!!! Another anot");
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


    class GsonInjector<T> implements MembersInjector<T> {
        private final Field field;
        private Gson gson;

        GsonInjector(Field field) {
            this.field = field;
            this.gson = new Gson();
            field.setAccessible(true);
        }

        public void injectMembers(T t) {
            try {
                field.set(t, gson);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }


}