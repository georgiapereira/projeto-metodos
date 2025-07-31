package src.model;

/**
 * NOVO MODELO: Representa o conjunto de avaliações de um único usuário.
 * Armazena múltiplos comentários e notas para calcular uma média.
 */
public class Review {
    private final String userLogin;
    private final String comment;
    private final int rating;

    public Review(String userLogin, String comment, int rating) {
        this.userLogin = userLogin;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
}