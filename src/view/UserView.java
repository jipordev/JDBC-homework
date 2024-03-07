package view;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

import static java.lang.System.*;

public class UserView {
    static Scanner scanner = new Scanner(in);
    static CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
    public static void printInfo(List<User> userList){
        Table tblUsers = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        tblUsers.addCell("     ID     ");
        tblUsers.addCell("     Name     ");
        tblUsers.addCell("     Email     ");
        tblUsers.addCell("     Password     ");
        tblUsers.addCell("     Is Deleted     ");
        tblUsers.addCell("     Is Verified     ");
        tblUsers.addCell("     UUID     ");
        for (User user : userList) {
            tblUsers.addCell(user.getId().toString(), cellStyle);
            tblUsers.addCell(user.getUserName(), cellStyle);
            tblUsers.addCell(user.getUserEmail(), cellStyle);
            tblUsers.addCell(hidePassword(user.getUserPassword()), cellStyle);
            tblUsers.addCell(user.getIsDeleted().toString(), cellStyle);
            tblUsers.addCell(user.getIsVerified().toString(), cellStyle);
            tblUsers.addCell(user.getUuid(), cellStyle);
        }
        out.println(tblUsers.render());
    }
    public static User createUser() {
        User createdUser = new User();
        UUID uuid = UUID.randomUUID();
        String shortUUID = uuid.toString().substring(0,9);
        out.print("Enter Username: ");
        createdUser.setUserName(scanner.nextLine());
        out.print("Enter Email: ");
        createdUser.setUserEmail(scanner.nextLine());
        out.print("Enter Password: ");
        createdUser.setUserPassword(scanner.nextLine());
        out.print("Enter Verification: (true/false) ");
        createdUser.setIsVerified(Boolean.parseBoolean(scanner.nextLine()));
        createdUser.setIsDeleted(false);
        createdUser.setUuid(shortUUID);
        return createdUser;
    }
    public static Integer deleteUser(List<User> userList){
        out.print(">> Enter user id you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        userList.removeIf(user -> user.getId().equals(id));
        return id;
    }

    public static void readOneUser(List<User> userList) {
        Table tblUsers = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        out.print(">> Enter user id you want to read: ");
        int id = Integer.parseInt(scanner.nextLine());
        tblUsers.addCell("     ID     ");
        tblUsers.addCell("     Name     ");
        tblUsers.addCell("     Email     ");
        tblUsers.addCell("     Password     ");
        tblUsers.addCell("     Is Deleted     ");
        tblUsers.addCell("     Is Verified     ");
        tblUsers.addCell("     UUID     ");
        boolean userFound = false;
        for (User user : userList) {
            if (user.getId().equals(id)) {
                tblUsers.addCell(user.getId().toString(), cellStyle);
                tblUsers.addCell(user.getUserName(), cellStyle);
                tblUsers.addCell(user.getUserEmail(), cellStyle);
                tblUsers.addCell(hidePassword(user.getUserPassword()), cellStyle);
                tblUsers.addCell(user.getIsDeleted().toString(), cellStyle);
                tblUsers.addCell(user.getIsVerified().toString(), cellStyle);
                tblUsers.addCell(user.getUuid(), cellStyle);
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            out.println("User not found.");
        } else {
            out.println(tblUsers.render());
        }
    }

    public static int options() {
        int choice;
        out.println("""
            ###########################
            1. Create
            2. Read 1 user
            3. Display all users
            4. Update user
            5. Delete user by id
            6. Search user by id
            7. Exit program
            ###########################""");
        out.print(">> Choose one option: ");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= 7) {
                return choice;
            } else {
                out.println("Please choose an option between [1-7]");
            }
        } catch (NumberFormatException e) {
            out.println("Invalid input. Please enter a number.");
        }
        return 0;
    }
    private static String hidePassword(String password) {
        return "*".repeat(password.length());
    }
    public static void searchById(List<User> userList) {
        Table tblUsers = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        out.print(">> Enter user id to search: ");
        int userId = Integer.parseInt(scanner.nextLine());
        tblUsers.addCell("     ID     ");
        tblUsers.addCell("     Name     ");
        tblUsers.addCell("     Email     ");
        tblUsers.addCell("     Password     ");
        tblUsers.addCell("     Is Deleted     ");
        tblUsers.addCell("     Is Verified     ");
        tblUsers.addCell("     UUID     ");
        boolean isFound = false;
        for (User user : userList) {
            if (user.getId().equals(userId)){
                tblUsers.addCell(user.getId().toString(), cellStyle);
                tblUsers.addCell(user.getUserName(), cellStyle);
                tblUsers.addCell(user.getUserEmail(), cellStyle);
                tblUsers.addCell(hidePassword(user.getUserPassword()), cellStyle);
                tblUsers.addCell(user.getIsDeleted().toString(), cellStyle);
                tblUsers.addCell(user.getIsVerified().toString(), cellStyle);
                tblUsers.addCell(user.getUuid(), cellStyle);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            out.println(tblUsers.render());
        } else {
            out.println("User not found");
        }
    }
    public static int updateById(List<User> userList) {
        out.print(">> Enter user id you want to update: ");
        int userId = Integer.parseInt(scanner.nextLine());
        for (User user : userList) {
            if (user.getId().equals(userId)) {
                out.print("Enter new user name: ");
                user.setUserName(scanner.nextLine());
                out.print("Enter new user email: ");
                user.setUserEmail(scanner.nextLine());
                out.print("Is user deleted? [true/false]: ");
                user.setIsDeleted(Boolean.parseBoolean(scanner.nextLine()));
                out.print("Is user verified? [true/false]: ");
                user.setIsVerified(Boolean.parseBoolean(scanner.nextLine()));
                out.print("Do you want to update password? [Y/N]: ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    out.print("Enter new password: ");
                    user.setUserPassword(scanner.nextLine());
                }
                return userId; // Return the user ID being updated
            }
        }
        return -1; // Indicate that the user ID was not found
    }

}
