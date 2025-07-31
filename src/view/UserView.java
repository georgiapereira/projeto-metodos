package src.view;

import src.model.Avaliacao;
import src.model.DeletedUser;
import src.model.User;
import java.util.List;

public class UserView {

    public void printUserDetails(User user) {
        System.out.println("Login: " + user.getlogin());
        System.out.println("Senha: " + user.getPassword());
    }

    public void printAllUsers(List<User> users) {
        System.out.println("\n--- Lista de Usuários Ativos ---");
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário ativo no sistema.");
            return;
        }
        for (User user : users) {
            printUserDetails(user);
            System.out.println("-----------");
        }
    }

    public void printDeletedUserDetails(DeletedUser deletedUser) {
        System.out.println("Login: " + deletedUser.getOriginalUser().getlogin());
        String justification = deletedUser.getJustificativaExclusao();
        if (justification != null && !justification.isEmpty()) {
            System.out.println("Justificativa da Exclusão: " + justification);
        } else {
            System.out.println("Justificativa da Exclusão: Não fornecida.");
        }
        int rating = deletedUser.getNotaExclusao();
        if (rating != -1) {
            System.out.println("Avaliação do Serviço (na exclusão): " + rating + "/5 estrelas.");
        } else {
            System.out.println("Avaliação do Serviço (na exclusão): Não fornecida.");
        }
    }

    public void printAllDeletedUsers(List<DeletedUser> deletedUsers) {
        System.out.println("\n--- Lista de Usuários Excluídos ---");
        if (deletedUsers.isEmpty()) {
            System.out.println("Nenhum usuário foi excluído ainda.");
            return;
        }
        for (DeletedUser deletedUser : deletedUsers) {
            printDeletedUserDetails(deletedUser);
            System.out.println("-----------");
        }
    }

    public void printPainelDeAvaliacoes(List<Avaliacao> avaliacoes) {
        System.out.println("\n--- PAINEL DE AVALIAÇÕES DO SERVIÇO ---");
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação foi feita ainda.");
            return;
        }
        for (Avaliacao avaliacao : avaliacoes) {
            System.out.println("Usuário: " + avaliacao.getUserLogin());
            System.out.printf("Nota Média: %.1f de 5 estrelas\n", avaliacao.getMediaDeNotas());
            System.out.println("Comentários Registrados:");
            List<String> comentarios = avaliacao.getComentarios();
            if (comentarios.isEmpty()) {
                System.out.println("  - Nenhum comentário.");
            } else {
                for (String comentario : comentarios) {
                    System.out.println("  - \"" + comentario + "\"");
                }
            }
            System.out.println("----------------------------------------");
        }
    }
    
    public void printErrorMessage(String message) {
        // Usar System.err é uma boa prática para mensagens de erro.
        System.err.println("ERRO: " + message);
    }
}