package src.service;

import src.model.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RAMReviewRepository implements ReviewRepository {
    private final HashMap<String, List<Review>> reviews = new HashMap<>();

    @Override
    public HashMap<String, List<Review>> getAllReviews() {
        return reviews;
    }

    @Override
    public void addReview(Review review) {
        reviews
            .computeIfAbsent(review.getUserLogin(), k -> new ArrayList<>())
            .add(review);
    }
}
