package ru.kirikomp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kirikomp.persist.Product;
import ru.kirikomp.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

//            ProductRepository productRepository = new ProductRepository(connection);
//            sc.setAttribute("productRepository", productRepository);
//
//            if (productRepository.findAll().size() == 0) {
//                productRepository.insert(new Product(1L, "Apple", 12L));
//                productRepository.insert(new Product(2L, "Bananas", 13L));
//                productRepository.insert(new Product(3L, "Tomato", 10L));
//                productRepository.insert(new Product(4L, "Yam", 8L));
//                productRepository.insert(new Product(5L, "Pineapple", 7L));
//                productRepository.insert(new Product(6L, "Potato", 11L));
//                productRepository.insert(new Product(7L, "Kiwi", 3L));
//                productRepository.insert(new Product(8L, "Lemon", 2L));
//                productRepository.insert(new Product(9L, "Pear", 13L));
//            }
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
