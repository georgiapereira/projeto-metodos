package src.view;

import src.model.User;
import java.util.List;

public class UserView {
    public void printUserDetails(User user) {
        System.out.println("Login: " + user.getlogin());
        System.out.println("Senha: " + user.getPassword());
    }

    public void printAllUsers(List<User> users) {
        for (User user : users) {
            printUserDetails(user);
            System.out.println("-----------");
        }
    }
}
