package src.service;

import src.model.Review;

import java.util.HashMap;
import java.util.List;

public interface ReviewRepository {
    HashMap<String, List<Review>> getAllReviews();
    void addReview(Review review);
}