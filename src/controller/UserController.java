package controller;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import view.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static final UserRepository userRepo = new UserRepositoryImpl();
    static List<User> users = userRepo.getAllUsers();
    static List<User> userList = new ArrayList<>();
    public void getUsers(){

        UserView.printInfo(users);
    }
    public void createUsers(){
        User user = UserView.createUser();
        userRepo.createUser(user);
    }
    public void deleteUsers(){
        int id = UserView.deleteUser(userList);
        userRepo.deleteUser(id);
    }
    public void readOneUser(){
        List<User> user = userRepo.getAllUsers();
        UserView.readOneUser(user);
    }
}
