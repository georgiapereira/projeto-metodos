package src.controller;

import src.model.Avaliacao;
import src.model.DeletedUser;
import src.model.User;
import src.model.exception.UserException;
import src.model.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia todas as listas de dados (ativos, excluídos e avaliações)
 * e possui todos os métodos necessários para suportar a camada de serviço.
 */
public class UserController {
    private List<User> users = new ArrayList<>();
    private List<DeletedUser> deletedUsers = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    /**
     * Adiciona um novo usuário à lista de usuários ativos.
     * @param user O objeto User a ser adicionado.
     * @throws UserException Se o login do usuário já estiver em uso.
     */
    public void addUser(User user) throws UserException {
        for (User existingUser : users) {
            if (existingUser.getlogin().equalsIgnoreCase(user.getlogin())) {
                throw new UserException("Falha de duplicidade: Usuário com o login '" + user.getlogin() + "' já existe.");
            }
        }
        users.add(user);
    }

    /**
     * Busca um usuário APENAS na lista de ativos. Usado para o fluxo de DELEÇÃO.
     * @param login O login do usuário a ser encontrado.
     * @param password A senha do usuário.
     * @return O objeto User correspondente.
     * @throws UserNotFoundException Se nenhum usuário ativo for encontrado com as credenciais.
     */
    public User findUserByCredentials(String login, String password) throws UserNotFoundException {
        for (User user : users) {
            if (user.getlogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Usuário não encontrado na lista de ativos ou senha incorreta.");
    }
    
    /**
     * Busca um usuário em AMBAS as listas (ativos e excluídos). Usado para o fluxo de AVALIAÇÃO.
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @return O objeto User correspondente, não importa o seu estado (ativo ou excluído).
     * @throws UserNotFoundException Se o usuário não for encontrado em nenhuma lista.
     */
    public User encontrarUsuarioEmQualquerLista(String login, String password) throws UserNotFoundException {
        // Procura na lista de usuários ativos primeiro
        for (User user : users) {
            if (user.getlogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        // Se não encontrar, procura na lista de usuários excluídos
        for (DeletedUser deletedUser : deletedUsers) {
            User originalUser = deletedUser.getOriginalUser();
            if (originalUser.getlogin().equals(login) && originalUser.getPassword().equals(password)) {
                return originalUser;
            }
        }
        // Se não encontrar em nenhuma lista, lança a exceção
        throw new UserNotFoundException("Usuário não encontrado ou senha incorreta.");
    }

    /**
     * Encontra o registro de avaliação de um usuário ou cria um novo se não existir.
     * @param login O login do usuário para o qual a avaliação será registrada.
     * @return O objeto Avaliacao existente ou um novo.
     */
    public Avaliacao encontrarOuCriarAvaliacao(String login) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getUserLogin().equals(login)) {
                return avaliacao;
            }
        }
        Avaliacao novaAvaliacao = new Avaliacao(login);
        avaliacoes.add(novaAvaliacao);
        return novaAvaliacao;
    }

    /**
     * Remove um usuário da lista de ativos e o adiciona à lista de excluídos.
     * @param user O objeto User a ser excluído.
     * @param justification A justificativa opcional para a exclusão.
     * @param rating A avaliação opcional dada no momento da exclusão.
     */
    public void deleteUser(User user, String justification, int rating) {
        users.remove(user);
        deletedUsers.add(new DeletedUser(user, justification, rating));
    }
    
    public List<User> getUsers() { return users; }
    public List<DeletedUser> getDeletedUsers() { return deletedUsers; }
    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
}