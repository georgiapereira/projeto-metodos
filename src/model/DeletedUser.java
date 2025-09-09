package src.model;

import java.io.Serial;
import java.io.Serializable;

public class DeletedUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    private final String deletionJustification;
    private final int deletionRating;
    private final User originalUser;

    public DeletedUser(User originalUser, String justification, int rating) {
        this.originalUser = originalUser;
        this.deletionJustification = justification;
        this.deletionRating = rating;
    }

    public String getDeletionJustification() {
        return deletionJustification;
    }

    public int getDeletionRating() {
        return deletionRating;
    }

    public User getOriginalUser() {
        return originalUser;
    }
}