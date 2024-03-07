package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.loadingProperties();

        try (Connection connection = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id")); // Use setId(Integer id)
                user.setUserName(resultSet.getString("user_name"));
                user.setUserEmail(resultSet.getString("user_email"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setIsDeleted(resultSet.getBoolean("is_deleted"));
                user.setIsVerified(resultSet.getBoolean("is_verified"));
                user.setUuid(resultSet.getString("user_uuid"));
                userList.add(user);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }
    @Override
    public void createUser(User user) {
        String sql = """
                    INSERT INTO users (user_name, user_email, is_deleted, user_password, is_verified, user_uuid)
                    VALUES (?,?,?,?,?,?)""";
        PropertiesLoader.loadingProperties();
        try(Connection connection = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setBoolean(3, user.getIsDeleted());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setBoolean(5, user.getIsVerified());
            preparedStatement.setString(6, user.getUuid());

            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("User created successfully");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteUser(Integer userId){
        String sql = "DELETE FROM users WHERE user_id = ?";
        PropertiesLoader.loadingProperties();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password"));
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1, userId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0 ){
                System.out.println("User deleted successfully");
            } else {
                System.out.println("User not found in database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public Optional<User> selectedById(Integer id) {
        return Optional.empty();
    }

}
