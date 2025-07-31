package src.service;

import src.model.DeletedUser;
import src.model.Review;
import src.model.User;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface ReviewRepository {
    HashMap<String, List<Review>> getAllReviews();
    void addReview(Review review);
}