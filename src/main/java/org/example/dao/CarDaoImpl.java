package org.example.dao;

import org.example.ConnectionUtil;
import org.example.model.User;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    @Override
    public User save(User car) {
        String sql = "INSERT INTO users (name , year) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getUsername());
            statement.setInt(2, car.getYear());
            int affectedRows = statement.executeUpdate();
            if (affectedRows < 1) {
                throw new RuntimeException("Expected to insert at least one row, but 0 rows ");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1,Long.class);
                car.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't add new car ", e);
        }
        return car;
    }

    @Override
    public User get(Long id) {
        User car = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                car = new User();
                car.setId(id);
                car.setName(name);
                car.setYear(year);

                System.out.println("id " + id + " name " + name + " year " + year);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to the db", e);
        }
        return car;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User update(User car) {
        return null;
    }

    @Override
    public boolean delete(User car) {
        return false;
    }
}
