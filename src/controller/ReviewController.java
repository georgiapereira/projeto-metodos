package src.controller;

import src.model.Review;
import src.service.ReviewRepository;
import src.util.LogManager;
import src.util.Logger;

import java.util.List;
import java.util.Map;

public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final Logger logger = LogManager.getLogger();

    public ReviewController(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public void addReview(String userLogin, String comment, int rating){
        Review review = new Review(userLogin, comment, rating);
        reviewRepository.addReview(review);
        logger.info("Avaliação criada por %s", review);
    }

    public Map<String, List<Review>> getAllReviews() {
        logger.info("Lista de avaliações requisitada");
        return reviewRepository.getAllReviews();
    }

}