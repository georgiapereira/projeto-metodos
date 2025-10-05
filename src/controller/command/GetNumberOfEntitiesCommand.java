package src.controller.command;

import src.controller.ReviewController;
import src.controller.UserController;

public class GetNumberOfEntitiesCommand implements Command {
    private final UserController userController;
    private final ReviewController reviewController;

    public GetNumberOfEntitiesCommand(UserController userController, ReviewController reviewController) {
        this.userController = userController;
        this.reviewController = reviewController;
    }

    @Override
    public Object execute(Object... args) {
        int activeUsers = userController.getActiveUsers().size();
        int deletedUsers = userController.getDeletedUsers().size();
        int reviews = reviewController.getAllReviews().size();

        return activeUsers + deletedUsers + reviews;
    }
}
