package src.view;

import src.controller.ControllerFacadeSingleton;
import src.model.Review;
import src.model.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReviewView {
    private final ControllerFacadeSingleton controllerFacade = ControllerFacadeSingleton.getInstance();

    public void manageLeaveReview(Scanner scanner) {
        System.out.println("\n--- Deixar uma Avaliação ---");

        User user = authenticate(scanner);

        if (user == null) {
            return;
        }

        System.out.println("\nPor favor, faça sua avaliação. Os campos são obrigatórios.");
        String comment;
        do {
            System.out.print("Comentário (limite 100 caracteres): ");
            comment = scanner.nextLine();
            if (comment.trim().isEmpty()) System.out.println("O comentário não pode ser vazio.");
            if (comment.length() > 100) System.out.println("Comentário muito longo. Limite de 100 caracteres.");
        } while (comment.trim().isEmpty() || comment.length() > 100);

        int nota = -1;
        do {
            System.out.print("Nota (de 0 a 5): ");
            try {
                nota = Integer.parseInt(scanner.nextLine());
                if (nota < 0 || nota > 5) System.out.println("A nota deve ser um número entre 0 e 5.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        } while (nota < 0 || nota > 5);


        controllerFacade.execute("addReview", user.getLogin(), comment, nota);
        System.out.println("Obrigado! Sua avaliação foi registrada com sucesso.");
    }

    public void manageViewReviewPanel() {
        Map<String, List<Review>> reviews = (Map<String, List<Review>>) controllerFacade.execute("getAllReviews");
        printReviewPanel(reviews);
    }

    private void printReviewPanel(Map<String, List<Review>> reviews) {
        System.out.println("\n--- PAINEL DE AVALIAÇÕES DO SERVIÇO ---");
        if (reviews.isEmpty()) {
            System.out.println("Nenhuma avaliação foi feita ainda.");
            return;
        }
        for (Map.Entry<String, List<Review>> userReviews : reviews.entrySet()) {
            System.out.println("Usuário: " + userReviews.getKey());
            System.out.printf("Nota Média: %.1f de 5 estrelas\n", getRatingAverage(userReviews.getValue()));
            System.out.println("Comentários Registrados:");

            for (Review review : userReviews.getValue()) {
                System.out.println("  - \"" + review.getComment() + "\"");
            }

            System.out.println("----------------------------------------");
        }
    }

    /**
     * Calcula a média de todas as notas recebidas.
     *
     * @return A média das notas ou 0 se não houver notas.
     */
    public Double getRatingAverage(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return 0.0;
        }
        // Usando streams para um cálculo mais moderno
        return reviews.stream()
                .map(Review::getRating)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    private User authenticate(Scanner scanner) {
        System.out.println("--- Autenticação de Usuário ---");
        System.out.print("Por favor, digite seu login: ");
        String login = scanner.nextLine();
        System.out.print("Agora, digite sua senha: ");
        String password = scanner.nextLine();

        User user = (User) controllerFacade.execute("login", login, password);
        if (user != null) {
            System.out.println("\nUsuário '" + user.getLogin() + "' autenticado.");
        }

        return user;
    }
}