package src.controller;

import src.controller.command.*;
import src.model.DeletedUser;
import src.model.Review;
import src.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerFacadeSingleton {
    private final ReviewController reviewController;
    private final UserController userController;
    private final Map<String, Command> cmds = new HashMap<>();

    private ControllerFacadeSingleton(ReviewController reviewController, UserController userController) {
        this.reviewController = reviewController;
        this.userController = userController;

        initCommands();
    }

    private void initCommands() {
        cmds.put("addReview", new AddReviewCommand(reviewController));
        cmds.put("getAllReviews", new GetAllReviewsCommand(reviewController));
        cmds.put("login", new LoginCommand(userController));
        cmds.put("addUser", new AddUserCommand(userController));
        cmds.put("deleteUser", new DeleteUserCommand(userController));
        cmds.put("getActiveUsers", new GetActiveUsersCommand(userController));
        cmds.put("getDeletedUsers", new GetDeletedUsersCommand(userController));
        cmds.put("getNumberOfEntities", new GetNumberOfEntitiesCommand(userController, reviewController));
    }

    private static ControllerFacadeSingleton instance;

    public static synchronized void setInstance(ReviewController reviewController, UserController userController) {
        if (instance == null) {
            instance = new ControllerFacadeSingleton(reviewController, userController);
        }
    }

    public static ControllerFacadeSingleton getInstance() {
        return instance;
    }

/**commands:<p>
 * "addReview" <br> args: userLogin:String comment:String rating:String<p>
 * "getAllReviews" <br> returns: Map (String -> [Review])<p>
 * "login" <br> returns: User <br> args: login:String password:String<p>
 * "deleteUser" <br> args: user:User justification:String rating:String<p>
 * "getAllActiveUsers" returns: [User]<p>
 * "getAllDeletedUsers" returns: [DeletedUser]<p>
 * "getNumberOfEntities" returns: int<p>
*/
    public Object execute(String cmd, Object... args) {
        Command c = cmds.get(cmd);
        return c.execute(args);
    }
}