package src.controller.command;

import src.controller.UserController;
import src.model.User;

public class DeleteUserCommand implements Command{
    private final UserController userController;
    public DeleteUserCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public Object execute(Object... args) {
        User user = (User) args[0];
        String justification = (String) args[1];
        int rating = (int) args[2];
        userController.deleteUser(user, justification, rating);
        return null;
    }
}
