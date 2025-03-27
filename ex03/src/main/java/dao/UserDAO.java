package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DataAcessObject {
    public UserDAO() {
        super();
        super.connect();
    }

    public boolean close() {
        return super.close();
    }

    public boolean insert(User user) {
        boolean isInserted = false;

        try {
            Statement statement = super.connection.createStatement();
            String sql = String.format(
                    "insert into users (id, name, email, age) values (%d, '%s', '%s', %d)",
                    user.getId(), user.getName(), user.getEmail(), user.getAge()
            );

            statement.executeUpdate(sql);
            statement.close();
            isInserted = true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return isInserted;
    }

    public User get(int id) {
        User user = null;

        try {
            Statement statement = super.connection.createStatement();
            String sql = String.format(
                    "select * from users where id=%d",
                    id
            );

            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                user = new User(id, name, email, age);
            }
            rs.close();
            statement.close();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return user;
    }

    public boolean delete(int id) {
        boolean isDeleted = false;
        try {
            Statement statement = super.connection.createStatement();
            String sql = String.format(
                    "delete from users where id = %d",
                    id
            );

            statement.executeUpdate(sql);
            statement.close();
            isDeleted = true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return isDeleted;
    }

    public boolean update(User user) {
        boolean isUpdated = false;

        try {
            Statement statement = super.connection.createStatement();
            String sql = String.format(
                    "update users set name = '%s', email = '%s', age = %d where id = %d",
                    user.getName(), user.getEmail(), user.getAge(), user.getId()
            );

            statement.executeUpdate(sql);
            statement.close();
            isUpdated = true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return isUpdated;
    }

    public List<User> read() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = super.connection.createStatement();
            String sql = "select * from users";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                users.add(new User(id, name, email, age));
            }
            rs.close();
            statement.close();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return users;
    }
}
