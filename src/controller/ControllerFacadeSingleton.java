package src.controller;

import src.model.DeletedUser;
import src.model.Review;
import src.model.User;

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
        int reviews = reviewController.getAllReviews().size();

        return activeUsers + deletedUsers + reviews;
    }
}