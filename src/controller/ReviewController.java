package src.controller;

import org.jetbrains.annotations.NotNull;
import src.controller.memento.Memento;
import src.controller.memento.MementoSource;
import src.model.Review;
import src.service.ReviewRepository;
import src.util.LogManager;
import src.util.Logger;

import java.util.List;
import java.util.Map;

public class ReviewController extends MementoSource<Map<String, List<Review>>> {
    private final ReviewRepository reviewRepository;
    private final Logger logger = LogManager.getLogger();

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        save();
    }

    @Override
    protected Memento<Map<String, List<Review>>> createMemento() {
        Memento<Map<String, List<Review>>> m = new Memento<>();
        Map<String, List<Review>> reviews = reviewRepository.getAllReviews();
        m.setState(reviews);
        return m;
    }

    @Override
    protected void restore(@NotNull Memento<Map<String, List<Review>>> m) {
        Map<String, List<Review>> reviews = m.getState();
        reviewRepository.setReviews(reviews);
    }

    public void addReview(String userLogin, String comment, int rating) {
        Review review = new Review(userLogin, comment, rating);
        reviewRepository.addReview(review);
        logger.info("Avaliação criada por %s", review);
    }

    public Map<String, List<Review>> getAllReviews() {
        logger.info("Lista de avaliações requisitada");
        return reviewRepository.getAllReviews();
    }
}