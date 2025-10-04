package src.controller.command;

import src.controller.ReviewController;

public class AddReviewCommand implements Command{
    private final ReviewController reviewController;
    public AddReviewCommand(ReviewController reviewController){
        this.reviewController = reviewController;
    }

    @Override
    public boolean doesUpdate(){
        return true;
    }

    @Override
    public Object execute(Object... args) {
        String userLogin = (String) args[0];
        String comment = (String) args[1];
        int rating = (int) args[2];
        reviewController.addReview(userLogin, comment, rating);
        return null;
    }
}
