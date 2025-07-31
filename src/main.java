package src;

import src.controller.UserController;
import src.service.UserService;
import src.view.UserView;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        UserController controller = new UserController();
        UserView view = new UserView();
        UserService service = new UserService(controller, view);
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
                    case 1: service.gerenciarCadastroUsuario(scanner); break;
                    case 2: service.gerenciarDeixarAvaliacao(scanner); break;
                    case 3: service.gerenciarVerPainelDeAvaliacoes(); break;
                    case 4: service.gerenciarListagemUsuariosAtivos(); break;
                    case 5: service.gerenciarListagemUsuariosExcluidos(); break;
                    case 6: service.gerenciarExclusaoUsuario(scanner); break;
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