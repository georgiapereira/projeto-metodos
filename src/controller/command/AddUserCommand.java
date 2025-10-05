package src.controller.command;

import src.controller.UserController;

public class AddUserCommand implements Command{
    private final UserController userController;
    public AddUserCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public boolean doesUpdate(){
        return true;
    }

    @Override
    public Object execute(Object... args) {
        String login = (String) args[0];
        String password = (String) args[1];
        return userController.addUser(login, password);
    }
}
