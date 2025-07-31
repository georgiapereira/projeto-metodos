package src.service;

import src.controller.UserController;
import src.model.Avaliacao;
import src.model.User;
import src.model.exception.UserNotFoundException;
import src.model.exception.UserException;
import src.view.UserView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserService {
    private UserController userController;
    private UserView userView;

    public UserService(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    public void gerenciarCadastroUsuario(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();
        
        System.out.println("----------------------------------------");
        System.out.println(">>> Processando a requisição de cadastro...");

        try {
            User newUser = new User(login, password);
            userController.addUser(newUser);
            System.out.println("Status: 201 Criado (Sucesso)");
            userView.printUserDetails(newUser);
        } catch (UserException e) {
            System.out.println("Status: Falha na Operação");
            userView.printErrorMessage(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    public void gerenciarDeixarAvaliacao(Scanner scanner) {
        System.out.println("\n--- Deixar uma Avaliação ---");
        System.out.print("Para começar, por favor, digite seu login: ");
        String login = scanner.nextLine();
        System.out.print("Agora, digite sua senha: ");
        String password = scanner.nextLine();

        try {

            User user = userController.encontrarUsuarioEmQualquerLista(login, password);
            
            System.out.println("\nUsuário '" + user.getlogin() + "' autenticado. Bem-vindo(a) ao portal de avaliação.");

            Avaliacao avaliacao = userController.encontrarOuCriarAvaliacao(user.getlogin());

            // Primeira avaliação (obrigatória)
            System.out.println("\nPor favor, faça sua primeira avaliação. Os campos são obrigatórios.");
            String comentario;
            do {
                System.out.print("Comentário (limite 100 caracteres): ");
                comentario = scanner.nextLine();
                if(comentario.trim().isEmpty()) System.out.println("O comentário não pode ser vazio.");
                if(comentario.length() > 100) System.out.println("Comentário muito longo. Limite de 100 caracteres.");
            } while (comentario.trim().isEmpty() || comentario.length() > 100);

            int nota = -1;
            do {
                System.out.print("Nota (de 0 a 5): ");
                try {
                    nota = Integer.parseInt(scanner.nextLine());
                    if(nota < 0 || nota > 5) System.out.println("A nota deve ser um número entre 0 e 5.");
                } catch (NumberFormatException e) { 
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
            } while (nota < 0 || nota > 5);

            avaliacao.adicionarAvaliacao(comentario, nota);
            System.out.println("Obrigado! Sua avaliação foi registrada com sucesso.");

            // Loop para avaliações seguintes (opcionais)
            while (true) {
                System.out.print("\nDeseja fazer outra avaliação? (digite 'sim' para continuar): ");
                if (!scanner.nextLine().equalsIgnoreCase("sim")) {
                    break;
                }
                
                System.out.println("\nNova avaliação (campos opcionais, pressione Enter para pular):");
                System.out.print("Comentário (limite 100 caracteres): ");
                comentario = scanner.nextLine();
                
                System.out.print("Nota (de 0 a 5): ");
                String notaInput = scanner.nextLine();
                nota = -1;
                if (!notaInput.isEmpty()) {
                    try { 
                        nota = Integer.parseInt(notaInput);
                        if(nota < 0 || nota > 5) nota = -1;
                    } catch (NumberFormatException e) { /* Ignora */ }
                }

                if (!comentario.isEmpty() || nota != -1) {
                    avaliacao.adicionarAvaliacao(comentario, nota);
                    System.out.println("Avaliação adicional registrada!");
                } else {
                    System.out.println("Nenhuma informação fornecida, avaliação adicional ignorada.");
                }
            }
        } catch (UserNotFoundException e) {
            userView.printErrorMessage(e.getMessage());
        }
    }

    public void gerenciarVerPainelDeAvaliacoes() {
        userView.printPainelDeAvaliacoes(userController.getAvaliacoes());
    }

    public void gerenciarListagemUsuariosAtivos() {
        userView.printAllUsers(userController.getUsers());
    }

    public void gerenciarListagemUsuariosExcluidos() {
        userView.printAllDeletedUsers(userController.getDeletedUsers());
    }

    public void gerenciarExclusaoUsuario(Scanner scanner) {
        System.out.println("\n--- Exclusão de Usuário ---");
        System.out.print("Digite o login do usuário a ser excluído: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        try {

            User userToDelete = userController.findUserByCredentials(login, password);

            System.out.println("\nATENÇÃO: Você tem certeza que deseja excluir o usuário '" + userToDelete.getlogin() + "'?");
            System.out.println("Esta ação é permanente e não pode ser desfeita.");
            System.out.print("Digite 'sim' para confirmar ou qualquer outra coisa para cancelar: ");
            String confirmacao = scanner.nextLine();
            
            final Set<String> opcoesSim = new HashSet<>(Arrays.asList("sim", "Sim", "SIM", "s", "S"));
            
            if (opcoesSim.contains(confirmacao)) {
                String justification = "";
                int rating = -1;
                System.out.println("\nO usuário será excluído. Você gostaria de justificar o motivo e avaliar o serviço?");
                System.out.println("1. Sim, justificar e avaliar");
                System.out.println("2. Não, apenas concluir exclusão");
                System.out.print("Escolha uma opção: ");
                String opcaoAvaliacao = scanner.nextLine();
                if (opcaoAvaliacao.equals("1")) {
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
                userController.deleteUser(userToDelete, justification, rating);
                System.out.println("\nUsuário '" + userToDelete.getlogin() + "' excluído com sucesso.");
            } else {
                System.out.println("\nOperação de exclusão cancelada pelo usuário.");
            }
        } catch (UserNotFoundException e) {
            userView.printErrorMessage(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }
}