package src.service;

import org.jetbrains.annotations.NotNull;
import src.controller.memento.Memento;
import src.model.Review;

import java.util.List;
import java.util.Map;

public interface ReviewRepository {
    Map<String, List<Review>> getAllReviews();
    void setReviews(Map<String, List<Review>> reviews);
    void addReview(Review review);
}