package ru.kirikomp;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("<h1>Hello, First Servlet!!</h1>");
    }

    @Override
    public String getServletInfo() {
        return "FirstServlet";
    }

    @Override
    public void destroy() {

    }
}
