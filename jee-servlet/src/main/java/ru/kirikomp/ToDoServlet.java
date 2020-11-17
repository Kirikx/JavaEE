package ru.kirikomp;

import ru.kirikomp.persist.ToDo;
import ru.kirikomp.persist.ToDoRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/"})
public class ToDoServlet extends HttpServlet {

    private ToDoRepository toDoRepository;

    @Override
    public void init() throws ServletException {
        this.toDoRepository = (ToDoRepository) getServletContext().getAttribute("todoRepository");
        if (toDoRepository == null) {
            throw new ServletException("ToDo repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getServletPath().equals("/")) {
            List<ToDo> todos = toDoRepository.findAll();
            req.setAttribute("todos", todos);
            getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
            } else if (req.getServletPath().equals("/todo")) {
                Long id = Long.valueOf(req.getParameter("id"));
                ToDo todo = toDoRepository.findById(id);
                req.setAttribute("todo", todo);
                getServletContext().getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(req, resp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
