package src.controller;

import src.controller.command.*;

import java.util.HashMap;
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
        cmds.put("undo", new UndoCommand(userController, reviewController));
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
 * "getAllActiveUsers" <br> returns: [User]<p>
 * "getAllDeletedUsers" <br> returns: [DeletedUser]<p>
 * "getNumberOfEntities" <br> returns: int<p>
 * "undo" <br> returns: boolean
*/
    public Object execute(String cmd, Object... args) {
        Command c = cmds.get(cmd);
        if(c.doesUpdate()){
            saveMementos();
        }
        Object result = c.execute(args);
        return result;
    }

    private void saveMementos(){
        userController.save();
        reviewController.save();
    }

    /**
     * NOVO MÉTODO: Gera um relatório com base no tipo fornecido.
     * Utiliza o padrão Template Method para construir o relatório.
     * @param reportType O tipo de relatório ("activeUsers", "deletedUsers", "reviews").
     * @return O relatório formatado como String.
     */
    public String generateReport(String reportType) {
        ReportGenerator reportGenerator;
        switch (reportType) {
            case "activeUsers":
                reportGenerator = new ActiveUsersReportGenerator();
                break;
            case "deletedUsers":
                reportGenerator = new DeletedUsersReportGenerator();
                break;
            case "reviews":
                reportGenerator = new ReviewsReportGenerator();
                break;
            default:
                return "Tipo de relatório desconhecido.";
        }
        return reportGenerator.generateReport();
    }
}