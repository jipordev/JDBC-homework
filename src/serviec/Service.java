package serviec;

import model.User;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<User> getAllUsers();
    void createUser(User user);
    Integer deleteUser(Integer userId);
    void updateUser(User user);
    Optional<User> selectedById(Integer userId);
}
