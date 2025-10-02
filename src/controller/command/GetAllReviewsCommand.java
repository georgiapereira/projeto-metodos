package src.controller.command;

import src.controller.ReviewController;

public class GetAllReviewsCommand implements Command{
    private final ReviewController reviewController;
    public GetAllReviewsCommand(ReviewController reviewController){
        this.reviewController = reviewController;
    }

    @Override
    public Object execute(Object... args) {
        return reviewController.getAllReviews();
    }
}
