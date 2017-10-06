package ru.ivmiit.repository;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import ru.ivmiit.models.User;

import java.sql.*;
import java.util.List;

/**
 * 05.10.2017
 * UsersRepositoryJdbcImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM student";

    //language=SQL
    private static final String SQL_SELECT_BY_AGE =
            "SELECT * FROM student WHERE age = ?";

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    public List<User> findAllByAge(int age) {
        List<User> users = Lists.newArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_AGE);
        preparedStatement.setInt(1, age);
        // resultSet - это итератор, указывающий перед
        // первым элементом результирующего множество
        ResultSet resultSet = preparedStatement.executeQuery(SQL_SELECT_ALL);
        // пока есть следующий элемент
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int currentAge = resultSet.getInt("age");
            User user = User.builder()
                    .id(id)
                    .name(name)
                    .age(currentAge)
                    .build();

            users.add(user);
        }
        return users;
    }

    public List<User> save(User model) {

        return null;
    }

    public void delete(Long id) {

    }

    public void update(User model) {

    }

    public User find(Long id) {
        return null;
    }

    @SneakyThrows
    public List<User> findAll() {
        List<User> users = Lists.newArrayList();
        Statement statement = connection.createStatement();
        // resultSet - это итератор, указывающий перед
        // первым элементом результирующего множество
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
        // пока есть следующий элемент
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            User user = User.builder()
                    .id(id)
                    .name(name)
                    .age(age)
                    .build();

            users.add(user);
        }
        return users;
    }
}
