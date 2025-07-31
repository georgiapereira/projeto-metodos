package src.model;

public class DeletedUser {
    private User originalUser;
    private String justificativaExclusao;
    private int notaExclusao;

    public DeletedUser(User originalUser, String justification, int rating) {
        this.originalUser = originalUser;
        this.justificativaExclusao = justification;
        this.notaExclusao = rating;
    }

    public User getOriginalUser() { return originalUser; }
    public String getJustificativaExclusao() { return justificativaExclusao; }
    public int getNotaExclusao() { return notaExclusao; }
}