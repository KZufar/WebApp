package ru.ivmiit.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * 05.10.2017
 * UsersRepositoryJdbcTemplateImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM student";

    //language=SQL
    private static final String SQL_SELECT_BY_AGE =
            "SELECT * FROM student WHERE age = ?";

    final String SQL_INSERT_STUDENT =
            "INSERT INTO student(name, age) VALUES (?, ?)";

    private RowMapper<User> rowMapper = (rs, rowNum) ->
            User.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .age(rs.getInt("age"))
            .build();
//
//
//    private RowMapper<User> rowMapperAnon = new RowMapper<User>() {
//
//        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return User
//                    .builder()
//                    .id(rs.getLong("id"))
//                    .name(rs.getString("name"))
//                    .age(rs.getInt("age"))
//                    .build();
//        }
//    };


    public List<User> findAllByAge(int age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE,
                rowMapper, age);
    }


    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL,
                rowMapper);
    }

    @Override
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

}
