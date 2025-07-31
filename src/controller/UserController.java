package src.controller;

import src.model.DeletedUser;
import src.model.User;
import src.model.UserValidator;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;
import src.service.UserRepository;

import java.util.*;

/**
 * Gerencia todas as listas de dados (ativos, excluídos e avaliações)
 * e possui todos os métodos necessários para suportar a camada de serviço.
 */
public class UserController {
    private final UserRepository userRepository;
    private final UserValidator userValidator = new UserValidator();

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(String login, String password) {
        try {
            String sanitizedLogin = login.trim();
            userValidator.validateLogin(sanitizedLogin);
            userValidator.validatePassword(sanitizedLogin, password);

            User newUser = new User(sanitizedLogin, password);
            userRepository.addUser(newUser);
            System.out.println("Status: 201 Criado (Sucesso)");
            return newUser;
        } catch (UserException e) {
            System.out.println("Status: Falha na Operação");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User authenticateWithCredentials(String login, String password) {
        try{
            return userRepository.findActiveUserByCredentials(login, password);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(User user, String justification, int rating) {
        try {
            User userToDelete = userRepository.findActiveUserByCredentials(user.getLogin(), user.getPassword());
            userRepository.deleteUser(user, justification, rating);
            System.out.println("\nUsuário '" + userToDelete.getLogin() + "' excluído com sucesso.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<User> getActiveUsers() {
        return userRepository.getAllActiveUsers();
    }
    public List<DeletedUser> getDeletedUsers() {
        return userRepository.getAllDeletedUsers();
    }
}