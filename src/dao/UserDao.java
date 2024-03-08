package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    User createUser(User user);
    Integer deleteUser(Integer integer);
    User updateUser(User user);
    Optional<User> selectedById(Integer id);
}
