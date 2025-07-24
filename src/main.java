package src;

import src.controller.UserController;
import src.view.UserView;
import src.model.User;
import java.util.List;

public class main {
    public static void main(String[] args) {
        UserController controller = new UserController();
        UserView view = new UserView();

        // Adicionando usuários
        controller.addUser(new User("João", "123456"));
        controller.addUser(new User("Maria", "maria1234"));

        // Exibindo todos os usuários
        List<User> usuarios = controller.getUsers();
        view.printAllUsers(usuarios);
    }
}
