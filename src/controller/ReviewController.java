package src.controller;

import src.model.Review;
import src.service.ReviewRepository;

import java.util.HashMap;
import java.util.List;

public class ReviewController {
    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public void addReview(String userLogin, String comment, int rating){
        Review review = new Review(userLogin, comment, rating);
        reviewRepository.addReview(review);
    }

    public HashMap<String, List<Review>> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

}