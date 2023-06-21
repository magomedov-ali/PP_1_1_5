package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL," +
                    "age TINYINT NOT NULL);");

//            System.out.println("Table was created.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS users;");

//            System.out.println("Table was dropped");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        final String SAVE = "INSERT INTO users (name, lastname, age) VALUES (?,?,?);";

        try (Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");

            User user = new User(name, lastName, age);

            if (resultSet.next()) {
                user.setAge(resultSet.getByte(1));
            }

            System.out.println("User with the name - " + user.getName() +
                    " was added to database");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

        final String DELETE = "DELETE FROM users WHERE id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

//            System.out.println("User with id " + id + " was removed from database");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");

            while (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;

    }

    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("TRUNCATE TABLE users;");

//            System.out.println("Table was cleaned");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
