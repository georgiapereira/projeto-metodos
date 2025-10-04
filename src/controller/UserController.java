package src.controller;

import org.jetbrains.annotations.NotNull;
import src.controller.memento.Memento;
import src.controller.memento.MementoSource;
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
public class UserController extends MementoSource<UserController.MementoHelperTuple> {
    private final Logger logger = LogManager.getLogger();
    private final UserRepository userRepository;
    private final UserValidator userValidator = new UserValidator();

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
        save();
    }

    @Override
    protected Memento<MementoHelperTuple> createMemento() {
        Memento<List<User>> userM = new Memento<>();
        userM.setState(userRepository.getAllActiveUsers());
        Memento<List<DeletedUser>> dUserM = new Memento<>();
        dUserM.setState(userRepository.getAllDeletedUsers());

        Memento<MementoHelperTuple> m = new Memento<>();
        m.setState(new MementoHelperTuple(userM, dUserM));
        return m;
    }

    @Override
    protected void restore(@NotNull Memento<MementoHelperTuple> m) {
        MementoHelperTuple mementos = m.getState();

        Memento<List<User>> userM = mementos.getUserM();
        List<User> activeUsers = userM.getState();
        userRepository.setActiveUsers(activeUsers);

        Memento<List<DeletedUser>> dUserM = mementos.getDUserM();
        List<DeletedUser> deletedUsers = dUserM.getState();
        userRepository.setDeletedUsers(deletedUsers);
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

    public static class MementoHelperTuple{
        Memento<List<User>> userM;
        Memento<List<DeletedUser>> dUserM;
        MementoHelperTuple(Memento<List<User>> userM, Memento<List<DeletedUser>> dUserM){
            this.userM = userM;
            this.dUserM = dUserM;
        }

        public Memento<List<User>> getUserM() {
            return userM;
        }

        public Memento<List<DeletedUser>> getDUserM() {
            return dUserM;
        }
    }
}