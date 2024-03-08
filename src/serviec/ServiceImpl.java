package serviec;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service{
    private static final UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public Optional<User> selectedById(Integer id) {
        return userDao.selectedById(id);
    }

}
