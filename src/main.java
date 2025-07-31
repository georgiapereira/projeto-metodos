package src;

import src.controller.ReviewController;
import src.controller.UserController;
import src.model.User;
import src.service.RAMReviewRepository;
import src.service.RAMUserRepository;
import src.service.ReviewRepository;
import src.service.UserRepository;
import src.view.ReviewView;
import src.view.UserView;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        UserRepository userRepository = new RAMUserRepository();
        UserController userController = new UserController(userRepository);
        UserView userView = new UserView(userController);

        ReviewRepository reviewRepository = new RAMReviewRepository();
        ReviewController reviewController = new ReviewController(reviewRepository);
        ReviewView reviewView = new ReviewView(reviewController);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("### Aplicação de Gerenciamento e Feedback Iniciada ###");

        while (running) {
            System.out.println("\n===== MENU DE OPÇÕES =====");
            System.out.println("1. Cadastrar Novo Usuário");
            System.out.println("2. Deixar uma Avaliação"); 
            System.out.println("3. Ver Painel de Avaliações");
            System.out.println("4. Listar Usuários Ativos");
            System.out.println("5. Listar Usuários Excluídos");
            System.out.println("6. Excluir um Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1: userView.manageUserCreation(scanner); break;
                    case 2: {
                        User user = userView.authenticate(scanner);
                        if(user == null) break;
                        reviewView.manageLeaveReview(scanner, user);
                        break;
                    }
                    case 3: reviewView.manageViewReviewPanel(); break;
                    case 4: userView.manageActiveUsersList(); break;
                    case 5: userView.manageDeletedUsersList(); break;
                    case 6: userView.manageUserDeletion(scanner); break;
                    case 0: running = false; break;
                    default: System.out.println("Opção inválida!"); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Por favor, digite um número.");
            }
        }
        scanner.close();
    }
}