package src.service;

import org.jetbrains.annotations.NotNull;
import src.controller.memento.Memento;
import src.model.DeletedUser;
import src.model.User;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;

import java.util.List;

public interface UserRepository {
    List<User> getAllActiveUsers();
    void setActiveUsers(List<User> activeUsers);

    List<DeletedUser> getAllDeletedUsers();
    void setDeletedUsers(List<DeletedUser> deletedUsers);

    /**
     * Adiciona um novo usuário à lista de usuários ativos.
     * @param user O objeto User a ser adicionado.
     * @throws UserException Se o login do usuário já estiver em uso.
     */
    void addUser(User user) throws UserException;

    /**
     * Remove um usuário da lista de ativos e o adiciona à lista de excluídos.
     * @param user O objeto User a ser excluído.
     * @param justification A justificativa opcional para a exclusão.
     * @param rating A avaliação opcional dada no momento da exclusão.
     */
    void deleteUser(User user, String justification, int rating);

    /**
     * Busca um usuário APENAS na lista de ativos. Usado para o fluxo de DELEÇÃO.
     * @param login O login do usuário a ser encontrado.
     * @param password A senha do usuário.
     * @return O objeto User correspondente.
     * @throws UserNotFoundException Se nenhum usuário ativo for encontrado com as credenciais.
     */
    User findActiveUserByCredentials(String login, String password) throws UserNotFoundException;

    /**
     * Busca um usuário em AMBAS as listas (ativos e excluídos). Usado para o fluxo de AVALIAÇÃO.
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @return O objeto User correspondente, não importa o seu estado (ativo ou excluído).
     * @throws UserNotFoundException Se o usuário não for encontrado em nenhuma lista.
     */
    User findAnyUserByCredentials(String login, String password) throws UserNotFoundException;
}
