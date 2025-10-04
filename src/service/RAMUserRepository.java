package src.service;

import src.model.DeletedUser;
import src.model.User;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RAMUserRepository implements UserRepository {
    private final List<User> activeUsers = new ArrayList<>();
    private final List<DeletedUser> deletedUsers = new ArrayList<>();

    @Override
    public List<User> getAllActiveUsers() {
        return activeUsers;
    }

    @Override
    public void setActiveUsers(List<User> activeUsers) {
        this.activeUsers.clear();
        this.activeUsers.addAll(activeUsers);
    }

    @Override
    public List<DeletedUser> getAllDeletedUsers() {
        return deletedUsers;
    }

    @Override
    public void setDeletedUsers(List<DeletedUser> deletedUsers) {
        this.deletedUsers.clear();
        this.deletedUsers.addAll(deletedUsers);
    }

    @Override
    public void addUser(User user) throws UserException {
        for (User existingUser: activeUsers) {
            if (existingUser.getLogin().equalsIgnoreCase(user.getLogin())) {
                throw new UserException("Falha de duplicidade: Usuário com o login '" + user.getLogin() + "' já existe.");
            }
        }
        activeUsers.add(user);
    }

    @Override
    public void deleteUser(User user, String justification, int rating) {
        activeUsers.remove(user);
        deletedUsers.add(new DeletedUser(user, justification, rating));
    }

    @Override
    public User findActiveUserByCredentials(String login, String password) throws UserNotFoundException {
        for (User user : activeUsers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Usuário não encontrado na lista de ativos ou senha incorreta.");
    }

    @Override
    public User findAnyUserByCredentials(String login, String password) throws UserNotFoundException {
        for (User user : activeUsers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        for (DeletedUser deletedUser : deletedUsers) {
            User originalUser = deletedUser.getOriginalUser();
            if (originalUser.getLogin().equals(login) && originalUser.getPassword().equals(password)) {
                return originalUser;
            }
        }

        throw new UserNotFoundException("Usuário não encontrado ou senha incorreta.");
    }
}