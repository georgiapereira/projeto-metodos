package src.controller.command;

import src.controller.UserController;

public class LoginCommand implements Command{
    private final UserController userController;
    public LoginCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public Object execute(Object... args) {
        String login = (String) args[0];
        String password = (String) args[1];
        return userController.authenticateWithCredentials(login, password);
    }
}
