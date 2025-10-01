package src.controller;

import src.model.DeletedUser;
import src.model.User;
import src.model.UserValidator;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;
import src.service.UserRepository;
import src.util.LogManager;
import src.util.Logger;

import java.util.*;

/**
 * Gerencia todas as listas de dados (ativos, excluídos e avaliações)
 * e possui todos os métodos necessários para suportar a camada de serviço.
 */
public class UserController {
    private final Logger logger = LogManager.getLogger();
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
            logger.info("Novo usuário cadastrado", newUser);
            return newUser;
        } catch (UserException e) {
            System.out.println("Status: Falha na Operação");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User authenticateWithCredentials(String login, String password) {
        try{
            User user = userRepository.findActiveUserByCredentials(login, password);
            logger.info("Usuário autenticado", user);
            return user;
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
            logger.info("Usuário removido", user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<User> getActiveUsers(){
        logger.info("Lista de usuários requisitada");
        return userRepository.getAllActiveUsers();
    }
    public List<DeletedUser> getDeletedUsers() {
        logger.info("Lista de usuários deletados requisitada");
        return userRepository.getAllDeletedUsers();
    }
}