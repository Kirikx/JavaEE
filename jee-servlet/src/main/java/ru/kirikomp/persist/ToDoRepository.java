package ru.kirikomp.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Named
@ApplicationScoped
public class ToDoRepository {

    @Inject
    private ServletContext context;

    private Connection conn;

    @PostConstruct
    public void init() throws SQLException {
        this.conn = (Connection) context.getAttribute("jdbcConnection");
        createTableIfNotExists(conn);
    }

    public void insert(ToDo toDo) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into todos(description, targetDate) values (?, ?);")) {
            stmt.setString(1, toDo.getDescription());
            stmt.setDate(2, Date.valueOf(toDo.getTargetDate()), Calendar.getInstance());
            stmt.execute();
        }
    }

    public void update(ToDo toDo) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update todos set description = ?, targetDate = ? where id = ?;")) {
            stmt.setString(1, toDo.getDescription());
            stmt.setDate(2, Date.valueOf(toDo.getTargetDate()), Calendar.getInstance());
            stmt.setLong(3, toDo.getId());
            stmt.execute();
        }
    }

    public void delete(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from todos where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public ToDo findById(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, description, targetDate from todos where id = ?;")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ToDo(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3, Calendar.getInstance()).toLocalDate());
            }
        }
        return new ToDo(-1L, "", null);
    }

    public List<ToDo> findAll() throws SQLException {
        List<ToDo> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, description, targetDate from todos");

            while (rs.next()) {
                res.add(new ToDo(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3, Calendar.getInstance()).toLocalDate()));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists todos (\n" +
                    "         id int auto_increment primary key, \n" +
                    "         description varchar(25), \n" +
                    "         targetDate date \n" +
                    ");");
        }
    }
}
