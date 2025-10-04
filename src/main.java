package src;

import src.controller.ControllerFacadeSingleton;
import src.controller.ReviewController;
import src.controller.UserController;
import src.service.CardapioService;
import src.service.PedidoService;
import src.service.RAMReviewRepository;
import src.service.RAMUserRepository;
import src.service.ReviewRepository;
import src.service.UserRepository;
import src.service.files.FileReviewRepository;
import src.service.files.FileUserRepository;
import src.view.ReviewView;
import src.view.UserView;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("### Aplicação de Gerenciamento e Feedback Iniciada ###");
        System.out.println("\nIniciando em modo de persistência de arquivo, deseja trocar para o modo de persistência em RAM?");
        System.out.print("Digite 's' para aceitar ou qualquer outra coisa para prosseguir com modo arquivo: ");
        String confirmation = scanner.nextLine();

        ReviewRepository reviewRepository;
        UserRepository userRepository;

        if (confirmation.equalsIgnoreCase("s")) {
            reviewRepository = new RAMReviewRepository();
            userRepository = new RAMUserRepository();
        } else {
            reviewRepository = new FileReviewRepository();
            userRepository = new FileUserRepository();
        }

        UserController userController = new UserController(userRepository);
        ReviewController reviewController = new ReviewController(reviewRepository);

        ControllerFacadeSingleton.setInstance(reviewController, userController);
        ControllerFacadeSingleton controllerFacade = ControllerFacadeSingleton.getInstance();

        UserView userView = new UserView();
        ReviewView reviewView = new ReviewView();

        //instanciando os serviços para cardápio e pedidos
        CardapioService cardapioService = new CardapioService();
        PedidoService pedidoService = new PedidoService();

        boolean running = true;

        while (running) {
            System.out.println("\n===== MENU DE OPÇÕES =====");
            System.out.println("1. Cadastrar Novo Usuário");
            System.out.println("2. Deixar uma Avaliação");
            System.out.println("3. Ver Painel de Avaliações");
            System.out.println("4. Listar Usuários Ativos");
            System.out.println("5. Listar Usuários Excluídos");
            System.out.println("6. Excluir um Usuário");
            System.out.println("7. Mostrar número total de entidades cadastradas");
            System.out.println("8. Ver Cardápio do Restaurante");
            System.out.println("9. Fazer Pedido (Item do Cardápio)");
            System.out.println("10. Fazer Pedido (Personalizado)"); 
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        userView.manageUserCreation(scanner);
                        break;
                    case 2:
                        reviewView.manageLeaveReview(scanner);
                        break;
                    case 3:
                        reviewView.manageViewReviewPanel();
                        break;
                    case 4:
                        userView.manageActiveUsersList();
                        break;
                    case 5:
                        userView.manageDeletedUsersList();
                        break;
                    case 6:
                        userView.manageUserDeletion(scanner);
                        break;
                    case 7:
                        System.out.printf("\nNúmero total de entidades cadastradas no sistema: %d\n", controllerFacade.getNumberOfEntities());
                        break;
                    case 8:
                        cardapioService.gerenciarVisualizacaoCardapio();
                        break;
                    case 9:
                        pedidoService.gerenciarPedidoPadrao(scanner);
                        break;
                    case 10:
                        pedidoService.gerenciarPedidoPersonalizado(scanner);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Por favor, digite um número.");
            }
        }
        scanner.close();
    }
}