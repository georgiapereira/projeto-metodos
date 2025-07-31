package src.model.exception;

/**
 * Exceção genérica para erros relacionados a operações com o usuário.
 */
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }
}