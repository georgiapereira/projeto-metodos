package src.view;

import src.controller.ControllerFacadeSingleton;
import java.util.Scanner;

public class ReportView {
    private final ControllerFacadeSingleton controllerFacade = ControllerFacadeSingleton.getInstance();

    public void manageReportGeneration(Scanner scanner) {
        boolean running = true;
        while(running) {
            System.out.println("\n--- Geração de Relatórios ---");
            System.out.println("1. Relatório de Usuários Ativos");
            System.out.println("2. Relatório de Usuários Excluídos");
            System.out.println("3. Relatório de Avaliações");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha o tipo de relatório: ");
            
            String option = scanner.nextLine();
            String report = null;

            switch (option) {
                case "1":
                    report = controllerFacade.generateReport("activeUsers");
                    break;
                case "2":
                    report = controllerFacade.generateReport("deletedUsers");
                    break;
                case "3":
                    report = controllerFacade.generateReport("reviews");
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            if (report != null) {
                System.out.println("\n----------------- RELATÓRIO GERADO -----------------");
                System.out.println(report);
                System.out.println("----------------- FIM DO RELATÓRIO -----------------");
            }
        }
    }
}