package src.model.exception;

// Exceção para quando um usuário não é encontrado para uma operação
public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message);
    }
}