package src.service;

import src.model.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReviewRepository {
    Map<String, List<Review>> getAllReviews();
    void addReview(Review review);
}