package src.model;

public class DeletedUser extends User {
    private final String deletionJustification;
    private final int deletionRating;

    public DeletedUser(User originalUser, String justification, int rating) {
        super(originalUser.getLogin(), originalUser.getPassword());
        this.deletionJustification = justification;
        this.deletionRating = rating;
    }

    public String getDeletionJustification() { return deletionJustification; }
    public int getDeletionRating() { return deletionRating; }
}