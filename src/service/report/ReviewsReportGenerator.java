package src.service.report;

import src.model.Review;
import java.util.List;
import java.util.Map;

public class ReviewsReportGenerator extends ReportGenerator {

    @Override
    protected String getHeader() {
        return "========== RELATÓRIO DE AVALIAÇÕES ==========\n\n";
    }

    @Override
    protected String getContent() {
        Map<String, List<Review>> allReviews = controllerFacade.getAllReviews();
        if (allReviews.isEmpty()) {
            return "Nenhuma avaliação encontrada no sistema.\n";
        }

        StringBuilder content = new StringBuilder();
        content.append(String.format("Total de usuários que avaliaram: %d%n%n", allReviews.size()));
        
        allReviews.forEach((login, reviews) -> {
            content.append(String.format("Usuário: %s%n", login));
            double average = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
            content.append(String.format("   Nota Média: %.1f%n", average));
            content.append("   Comentários:%n");
            reviews.forEach(review -> {
                content.append(String.format("     - \"%s\" (Nota: %d)%n", review.getComment(), review.getRating()));
            });
            content.append("\n");
        });
        
        return content.toString();
    }
}