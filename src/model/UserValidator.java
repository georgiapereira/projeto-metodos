package src.model;

import src.model.exception.InvalidLoginException;
import src.model.exception.UserException;

import java.util.regex.Pattern;


public class UserValidator {
    public void validateLogin(String login) throws UserException {
        if (login == null || login.isEmpty()) {
            throw new InvalidLoginException("Erro: O login não pode ser vazio ou conter apenas espaços.");
        }

        if (login.length() > 12) {
            throw new InvalidLoginException("Erro: O login não pode conter mais de 12 caracteres.");
        }

        if (login.matches(".*\\d.*")) {
            throw new InvalidLoginException("Erro: O login não pode conter números.");
        }
    }

    public void validatePassword(String login, String password) throws UserException {
        if (password == null || password.length() < 8) {
            throw new InvalidLoginException("Erro: A senha deve conter pelo menos 8 caracteres.");
        }
        if (password.length() > 128) {
            throw new InvalidLoginException("Erro: A senha não pode conter mais de 128 caracteres.");
        }
        int requiredCharacterCount = 0;
        final Pattern[] inputRegexes = new Pattern[4];
        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*\\d.*");
        inputRegexes[3] = Pattern.compile(".*[!@#$%^&*()\\-_=+|\\[{\\]}'].*");
        for (Pattern inputRegex : inputRegexes){
            if(inputRegex.matcher(password).matches()){
                requiredCharacterCount++;
            }
        }
        if (requiredCharacterCount < 3) {
            throw new InvalidLoginException("Erro: A senha deve conter no mínimo três dos seguintes tipos de caracteres: maiúsculas, minúsculas, números e caracteres não alfanuméricos (! @ # $ % ^ & * ( ) _ + - = [ ] { } | ')");
        }
        if (password.equals(login)) {
            throw new InvalidLoginException("Erro: A senha não pode ser idêntica ao login.");
        }
    }
}
