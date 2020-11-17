package ru.kirikomp;

import ru.kirikomp.persist.Product;
import ru.kirikomp.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/product", "/product/*"})
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("Product repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
                List<Product> products = productRepository.findAll();
                req.setAttribute("products", products);
                getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
            } else if (req.getPathInfo().equals("/edit")) {
                Long id = Long.valueOf(req.getParameter("id"));
                Product product = productRepository.findById(id);
                req.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
