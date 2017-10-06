package ru.ivmiit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String name1 = req.getParameter("name");
        String password1 = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
        if(name1 == null && password1 == null){
            writer.write("Please, fill in the gaps / Пожалуйста, заполните пропущенные поля");
        }else{
            writeToBd(name1, password1);
            resp.getWriter().println("<!DOCTYPE HTML>");
            resp.getWriter().println("<html><body><p>" + "Вы успешно зарегистрировались!" + "</p></body></html>");
        }
    }


    public void writeToBd(String name1, String password1){
        try {
            final String SQL_INSERT_STUDENT =
                    "INSERT INTO form(name, password) VALUES (?, ?)";

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/student",
                    "postgres",
                    "1234"
            );

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT);
            preparedStatement.setString(1, name1);
            preparedStatement.setString(2, password1);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

