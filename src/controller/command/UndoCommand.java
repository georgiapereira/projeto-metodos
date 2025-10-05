package src.controller.command;

import src.controller.ReviewController;
import src.controller.UserController;
import src.controller.memento.Memento;

public class UndoCommand implements Command{
    private final UserController userController;
    private final ReviewController reviewController;

    public UndoCommand(UserController userController, ReviewController reviewController) {
        this.userController = userController;
        this.reviewController = reviewController;
    }

    @Override
    public Object execute(Object... args) {
        boolean userDidUndo = userController.undo();
        boolean reviewDidUndo = reviewController.undo();
        return userDidUndo || reviewDidUndo;
    }
}
