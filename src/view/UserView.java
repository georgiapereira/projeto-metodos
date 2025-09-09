package src.view;

import src.controller.ControllerFacadeSingleton;
import src.model.DeletedUser;
import src.model.User;

import java.util.*;

public class UserView {
    private final ControllerFacadeSingleton controllerFacade = ControllerFacadeSingleton.getInstance();

    public void manageUserCreation(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        System.out.println("----------------------------------------");
        System.out.println(">>> Processando a requisição de cadastro...");

        User newUser = controllerFacade.addUser(login, password);
        if (newUser != null) {
            printUserDetails(newUser);
        }
        System.out.println("----------------------------------------");

    }

    public void manageActiveUsersList() {
        printAllUsers(controllerFacade.getActiveUsers());
    }

    public void manageDeletedUsersList() {
        printAllDeletedUsers(controllerFacade.getDeletedUsers());
    }

    public void manageUserDeletion(Scanner scanner) {
        System.out.println("\n--- Exclusão de Usuário ---");
        System.out.print("Digite o login do usuário a ser excluído: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        User userToDelete = controllerFacade.authenticateWithCredentials(login, password);
        if (userToDelete == null) {
            return;
        }

        System.out.println("\nATENÇÃO: Você tem certeza que deseja excluir o usuário '" + userToDelete.getLogin() + "'?");
        System.out.println("Esta ação é permanente e não pode ser desfeita.");
        System.out.print("Digite 's' para confirmar ou qualquer outra coisa para cancelar: ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equals("s")) {
            System.out.println("\nOperação de exclusão cancelada pelo usuário.");
            return;
        }

        String justification = "";
        int rating = -1;

        System.out.println("\nO usuário será excluído. Você gostaria de justificar o motivo e avaliar o serviço?");
        System.out.println("1. Sim, justificar e avaliar");
        System.out.println("2. Não, apenas concluir exclusão");
        System.out.print("Escolha uma opção: ");

        String evaluationOption = scanner.nextLine();
        if (evaluationOption.equals("1")) {
            System.out.print("Por favor, digite sua justificativa (opcional, pressione Enter para pular): ");
            justification = scanner.nextLine();
            System.out.print("Avalie nosso serviço de 0 a 5 estrelas (opcional, pressione Enter para pular): ");
            String ratingInput = scanner.nextLine();
            if (!ratingInput.isEmpty()) {
                try {
                    rating = Integer.parseInt(ratingInput);
                    if (rating < 0 || rating > 5) {
                        System.out.println("Avaliação fora do intervalo (0-5), será ignorada.");
                        rating = -1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Avaliação inválida, será ignorada.");
                }
            }
        }

        controllerFacade.deleteUser(userToDelete, justification, rating);

        System.out.println("----------------------------------------");
    }

    private void printUserDetails(User user) {
        System.out.println("Login: " + user.getLogin());
        System.out.println("Senha: " + user.getPassword());
    }

    private void printAllUsers(List<User> users) {
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

    private void printDeletedUserDetails(DeletedUser deletedUser) {
        System.out.println("Login: " + deletedUser.getOriginalUser().getLogin());
        String justification = deletedUser.getDeletionJustification();
        if (justification != null && !justification.isEmpty()) {
            System.out.println("Justificativa da Exclusão: " + justification);
        } else {
            System.out.println("Justificativa da Exclusão: Não fornecida.");
        }
        int rating = deletedUser.getDeletionRating();
        if (rating != -1) {
            System.out.println("Avaliação do Serviço (na exclusão): " + rating + "/5 estrelas.");
        } else {
            System.out.println("Avaliação do Serviço (na exclusão): Não fornecida.");
        }
    }

    private void printAllDeletedUsers(List<DeletedUser> deletedUsers) {
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
}