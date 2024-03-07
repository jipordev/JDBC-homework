package view;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.*;

public class UserView {
    static Scanner scanner = new Scanner(in);
    static Table tblUsers = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
    static CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
    public static void printInfo(List<User> userList){
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
        out.print(">> Enter user id you want to read: ");
        int id = Integer.parseInt(scanner.nextLine());
        tblUsers.addCell("     ID     ");
        tblUsers.addCell("     Name     ");
        tblUsers.addCell("     Email     ");
        tblUsers.addCell("     Password     ");
        tblUsers.addCell("     Is Deleted     ");
        tblUsers.addCell("     Is Verified     ");
        tblUsers.addCell("     UUID     ");
        for (User user : userList) {
            if (user.getId().equals(id)){
                tblUsers.addCell(user.getId().toString(), cellStyle);
                tblUsers.addCell(user.getUserName(), cellStyle);
                tblUsers.addCell(user.getUserEmail(), cellStyle);
                tblUsers.addCell(hidePassword(user.getUserPassword()), cellStyle);
                tblUsers.addCell(user.getIsDeleted().toString(), cellStyle);
                tblUsers.addCell(user.getIsVerified().toString(), cellStyle);
                tblUsers.addCell(user.getUuid(), cellStyle);
                out.println(tblUsers.render());
            }
        }
    }

    public static int options(){
        out.println("""
                ###########################
                1. Create
                2. Read 1 user
                3. Display all users
                4. Update user
                5. Delete user by id
                6. Search user by name
                7. Search user by id
                8. Exit program
                ###########################""");
        out.print(">> Choose one option: ");
        return Integer.parseInt(scanner.nextLine());
    }
    private static String hidePassword(String password) {
        return "*".repeat(password.length());
    }
}
