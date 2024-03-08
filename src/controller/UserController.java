package controller;

import model.User;
import dao.UserDao;
import dao.UserDaoImpl;
import serviec.Service;
import serviec.ServiceImpl;
import view.UserView;


import java.util.List;



public class UserController {
    private static final UserDao userRepo = new UserDaoImpl();
    static List<User> users = userRepo.getAllUsers();
    static Service service = new ServiceImpl();
    public void getUsers(){
        UserView.printInfo(service.getAllUsers());
    }
    public void createUsers(){
        User user = UserView.createUser();
        service.createUser(user);
    }
    public void deleteUsers(){
        int id = UserView.deleteUser(users);
        service.deleteUser(id);
    }
    public void readOneUser(){
        UserView.readOneUser(users);
    }
    // Inside the UserController class
    public void searchUsers() {
        UserView.searchById(users);
    }
    public void updateUser() {
        User user = UserView.updateById(users);
        assert user != null;
        service.updateUser(user);
    }
}
