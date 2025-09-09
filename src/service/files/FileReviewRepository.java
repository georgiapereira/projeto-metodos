package src.service.files;

import src.model.Review;
import src.service.ReviewRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileReviewRepository implements ReviewRepository {
    private final String path = "db/review.ser";

    @Override
    public Map<String, List<Review>> getAllReviews() {
        return getReviewList().stream().collect(Collectors.groupingBy(Review::getUserLogin));
    }

    private List<Review> getReviewList() {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Review> reviews = (List<Review>) objectInputStream.readObject();
            objectInputStream.close();

            return reviews;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void addReview(Review review) {
        List<Review> allReviews = getReviewList();
        allReviews.add(review);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allReviews);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Falha na criação de arquivo");
        }
    }
}
