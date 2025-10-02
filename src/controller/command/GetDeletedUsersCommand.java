package src.controller.command;

import src.controller.UserController;

public class GetDeletedUsersCommand implements Command{
    private final UserController userController;
    public GetDeletedUsersCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public Object execute(Object... args) {
        return userController.getDeletedUsers();
    }
}
