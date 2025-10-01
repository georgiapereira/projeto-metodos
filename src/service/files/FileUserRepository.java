package src.service.files;

import src.model.DeletedUser;
import src.model.User;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;
import src.service.UserRepository;
import src.util.LogManager;
import src.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepository implements UserRepository {
    private final Logger logger = LogManager.getLogger();
    private final String userPath = "db/user.ser";
    private final String deletedUserPath = "db/deleted-user.ser";

    @Override
    public List<User> getAllActiveUsers(){
        try (FileInputStream fileInputStream = new FileInputStream(userPath)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> users = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
            return users;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("Erro de leitura de arquivo", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeletedUser> getAllDeletedUsers() {
        try (FileInputStream fileInputStream = new FileInputStream(deletedUserPath)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<DeletedUser> deletedUsers = (List<DeletedUser>) objectInputStream.readObject();
            objectInputStream.close();
            return deletedUsers;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("Erro de leitura de arquivo", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) throws UserException {
        List<User> activeUsers = getAllActiveUsers();
        for (User existingUser: activeUsers) {
            if (existingUser.getLogin().equalsIgnoreCase(user.getLogin())) {
                throw new UserException("Falha de duplicidade: Usuário com o login '" + user.getLogin() + "' já existe.");
            }
        }

        activeUsers.add(user);

        try (FileOutputStream fileOutputStream = new FileOutputStream(userPath)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(activeUsers);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            logger.error("Erro de escrita de arquivo", e);
            throw new RuntimeException("Falha na criação de arquivo", e);
        }
    }

    @Override
    public void deleteUser(User user, String justification, int rating) {
        List<User> users = getAllActiveUsers();
        users.remove(user);

        try (FileOutputStream fileOutputStream = new FileOutputStream(userPath)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            logger.error("Erro de escrita de arquivo", e);
            throw new RuntimeException("Falha na criação de arquivo");
        }

        DeletedUser deletedUser = new DeletedUser(user, justification, rating);

        List<DeletedUser> deletedUsers = getAllDeletedUsers();
        deletedUsers.add(deletedUser);

        try (FileOutputStream fileOutputStream = new FileOutputStream(deletedUserPath)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(deletedUsers);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            logger.error("Erro de escrita de arquivo", e);
            throw new RuntimeException("Falha na criação de arquivo");
        }
    }

    @Override
    public User findActiveUserByCredentials(String login, String password) throws UserNotFoundException {
        for (User user : getAllActiveUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Usuário não encontrado na lista de ativos ou senha incorreta.");
    }

    @Override
    public User findAnyUserByCredentials(String login, String password) throws UserNotFoundException {
        for (User user : getAllActiveUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        for (DeletedUser deletedUser : getAllDeletedUsers()) {
            User originalUser = deletedUser.getOriginalUser();
            if (originalUser.getLogin().equals(login) && originalUser.getPassword().equals(password)) {
                return originalUser;
            }
        }

        throw new UserNotFoundException("Usuário não encontrado ou senha incorreta.");
    }
}