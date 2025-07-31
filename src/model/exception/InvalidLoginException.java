package src.model.exception;

/**
 * Exceção específica para quando um login é inválido
 * (nulo, vazio ou com apenas espaços).
 * Herda de UserException.
 */
public class InvalidLoginException extends UserException {
    public InvalidLoginException(String message) {
        super(message);
    }
}