package servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;

public class GuiceListtener extends GuiceServletContextListener {
    private static final Logger logger = LogManager.getLogger(GuiceListtener.class);

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
            }
        });
    }


}