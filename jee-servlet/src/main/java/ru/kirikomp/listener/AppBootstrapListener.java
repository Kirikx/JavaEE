package ru.kirikomp.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kirikomp.persist.ToDo;
import ru.kirikomp.persist.ToDoRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

@WebListener
public class AppBootstrapListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing application");

        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute("jdbcConnection", connection);

        } catch (SQLException ex) {
            logger.error("Can`t initialize JDBC connection", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Destroyed application. Closing JDBC connection");

        ServletContext sc = sce.getServletContext();
        Connection jdbcConnection = (Connection) sc.getAttribute("jdbcConnection");
        try {
            if (jdbcConnection != null && jdbcConnection.isClosed()) {
                    jdbcConnection.close();
            }
        } catch (SQLException ex) {
            logger.error("Can`t close JDBC connection", ex);
        }
    }
}
