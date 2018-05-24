package servlet;


import com.google.inject.Guice;

public class GuiceConfigServlet {
    Guice.createInjector(new GuiceConfigServlet());
}
