package src.model;

import src.model.exception.InvalidLoginException;

public class User {
    private String login;
    private String password;

    public User(String login, String password) throws InvalidLoginException {
        // 1. Validação de Nulo
        if (login == null) {
            throw new InvalidLoginException("Erro: O login não pode ser nulo.");
        }
        
        // 2. Sanitização: Remove espaços no início e no fim
        String sanitizedLogin = login.trim();

        // 3. Validação de Vazio: Verifica se, após a limpeza, o login ficou vazio
        if (sanitizedLogin.isEmpty()) {
            throw new InvalidLoginException("Erro: O login não pode ser vazio ou conter apenas espaços.");
        }

        // 4. Verifica se há espaços no meio do login
        if (sanitizedLogin.contains(" ")) {
            throw new InvalidLoginException("Erro: O login não pode conter espaços.");
        }
        
        this.login = sanitizedLogin;
        this.password = password;
    }

    public String getlogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}