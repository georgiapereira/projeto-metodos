package src.controller;

import src.model.DeletedUser;
import src.model.Review;
import src.model.User;
import src.service.report.ActiveUsersReportGenerator;
import src.service.report.DeletedUsersReportGenerator;
import src.service.report.ReportGenerator;
import src.service.report.ReviewsReportGenerator;

import java.util.List;
import java.util.Map;

public class ControllerFacadeSingleton {
    private final ReviewController reviewController;
    private final UserController userController;

    private ControllerFacadeSingleton(ReviewController reviewController, UserController userController) {
        this.reviewController = reviewController;
        this.userController = userController;
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

    public void addReview(String userLogin, String comment, int rating) {
        reviewController.addReview(userLogin, comment, rating);
    }

    public Map<String, List<Review>> getAllReviews() {
        return reviewController.getAllReviews();
    }

    public User addUser(String login, String password) {
        return userController.addUser(login, password);
    }

    public User authenticateWithCredentials(String login, String password) {
        return userController.authenticateWithCredentials(login, password);
    }

    public void deleteUser(User user, String justification, int rating) {
        userController.deleteUser(user, justification, rating);
    }

    public List<User> getActiveUsers() {
        return userController.getActiveUsers();
    }

    public List<DeletedUser> getDeletedUsers() {
        return userController.getDeletedUsers();
    }

    public int getNumberOfEntities() {
        int activeUsers = userController.getActiveUsers().size();
        int deletedUsers = userController.getDeletedUsers().size();
        int reviews = reviewController.getAllReviews().values().stream().mapToInt(List::size).sum();

        return activeUsers + deletedUsers + reviews;
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