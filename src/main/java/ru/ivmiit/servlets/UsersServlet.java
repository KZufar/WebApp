package ru.ivmiit.servlets;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.models.User;
import ru.ivmiit.repository.UsersRepository;
import ru.ivmiit.repository.UsersRepositoryJdbcImpl;
import ru.ivmiit.repository.UsersRepositoryJdbcTemplateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

/**
 * 05.10.2017
 * UsersServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        // Загрузил драйвер в систему Java
//        Class.forName("org.postgresql.Driver");
//        Connection connection = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/students_09_622",
//                "postgres",
//                "qwerty007"
//        );
//        usersRepository = new UsersRepositoryJdbcImpl(connection);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/student");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        if (req.getParameter("age") != null) {
            users = usersRepository.findAllByAge(Integer.parseInt(req.getParameter("age")));
        } else {
            users = usersRepository.findAll();
        }

//        PrintWriter writer = resp.getWriter();
//        writer.write("<table>");
//        writer.write("<tr>");
//        writer.write("<th>ID</th>");
//        writer.write("<th>Имя</th>");
//        writer.write("<th>Возраст</th>");
//        writer.write("</tr>");
//        for (User user : users) {
//            writer.write("<tr>");
//            writer.write("<td>" + user.getId() + "</td>");
//            writer.write("<td>" + user.getName() + "</td>");
//            writer.write("<td>" + user.getAge() + "</td>");
//            writer.write("</tr>");
//        }
//        writer.write("</table>");
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}
