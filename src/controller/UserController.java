package controller;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import view.UserView;


import java.util.List;


public class UserController {
    private static final UserRepository userRepo = new UserRepositoryImpl();
    static List<User> users = userRepo.getAllUsers();
    public void getUsers(){
        UserView.printInfo(userRepo.getAllUsers());
    }
    public void createUsers(){
        User user = UserView.createUser();
        userRepo.createUser(user);
    }
    public void deleteUsers(){
        int id = UserView.deleteUser(users);
        userRepo.deleteUser(id);
    }
    public void readOneUser(){
        List<User> user = userRepo.getAllUsers();
        UserView.readOneUser(user);
    }
    public void searchUsers(){
        UserView.searchById(users);
    }

    public void updateUser() {
        int id = UserView.updateById(users);
        if (id != -1) {
            for (User user : users) {
                if (user.getId().equals(id)) {
                    userRepo.updateUser(user);
                    System.out.println("User with ID " + id + " updated successfully.");
                    break;
                }
            }
        } else {
            System.out.println("User not found or updated.");
        }
    }


}
