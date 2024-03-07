import controller.UserController;
import view.UserView;

public class Main {
    static UserController userController = new UserController();
    public static void main(String[] args) {
        do {
            switch (UserView.options()){
                case 1 -> userController.createUsers();
                case 2 -> userController.readOneUser();
                case 3 -> userController.getUsers();
                case 4 -> userController.updateUser();
                case 5 -> userController.deleteUsers();
                case 6 -> userController.searchUsers();
                case 7 -> System.exit(0);
            }
        } while (true);
    }
}