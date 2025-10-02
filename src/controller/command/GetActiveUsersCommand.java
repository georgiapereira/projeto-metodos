package src.controller.command;

import src.controller.UserController;

public class GetActiveUsersCommand implements Command{
    private final UserController userController;
    public GetActiveUsersCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public Object execute(Object... args) {
        return userController.getActiveUsers();
    }
}
