package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties dbProperties = new Properties();
            dbProperties.put("user", "root");
            dbProperties.put("password", "Pasternak16-");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/car", dbProperties);
            String sql = "SELECT * FROM car WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, 2L);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getObject("id", Long.class);
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");

                System.out.println("id " + id + " name " + name + " email " + email);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't load JDBC driver", e);
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to the db", e);
        }
        System.out.println("Hello world!");
    }
}